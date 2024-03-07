package com.sky.wanxinp2p.consumer.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.wanxinp2p.account.model.AccountDTO;
import com.sky.wanxinp2p.account.model.AccountRegisterDTO;
import com.sky.wanxinp2p.common.domain.BusinessException;
import com.sky.wanxinp2p.common.domain.CodePrefixCode;
import com.sky.wanxinp2p.common.domain.CommonErrorCode;
import com.sky.wanxinp2p.common.domain.RestResponse;
import com.sky.wanxinp2p.common.util.CodeNoUtil;
import com.sky.wanxinp2p.consumer.agent.AccountApiAgent;
import com.sky.wanxinp2p.consumer.entity.Consumer;
import com.sky.wanxinp2p.consumer.mapper.ConsumerMapper;
import com.sky.wanxinp2p.consumer.model.ConsumerDTO;
import com.sky.wanxinp2p.consumer.common.ConsumerErrorCode;
import com.sky.wanxinp2p.consumer.model.ConsumerRegisterDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer> implements ConsumerService {

    @Resource
    private AccountApiAgent accountApiAgent;

    /**
     * 检测用户是否存在
     *
     * @param mobile
     * @return
     */
    @Override
    public Integer checkMobile(String mobile) {
        return getByMobile(mobile, false) == null ? 0 : 1;
    }

    /**
     * 根据手机号获取用户信息
     *
     * @param mobile  用户手机号
     * @param throwEx 不存在是否抛出异常
     * @return
     */
    private ConsumerDTO getByMobile(String mobile, Boolean throwEx) {
        Consumer consumer = this.getOne(lambdaQuery().eq(Consumer::getMobile, mobile));
        return convertConsumerEntityTODTO(consumer);
    }

    private ConsumerDTO convertConsumerEntityTODTO(Consumer consumer) {
        if (consumer == null) {
            return null;
        }
        ConsumerDTO consumerDTO = new ConsumerDTO();
        BeanUtils.copyProperties(consumer, consumerDTO);
        return consumerDTO;
    }

    /**
     * 用户注册
     *
     * @param consumerRegisterDTO
     * @return
     */
    @Override
    public void register(ConsumerRegisterDTO consumerRegisterDTO) {
        if (checkMobile(consumerRegisterDTO.getMobile()) == 1) {
            throw new BusinessException(ConsumerErrorCode.E_140107);
        }
        Consumer consumer = new Consumer();
        BeanUtils.copyProperties(consumerRegisterDTO,consumer);
        consumer.setUserNo(CodeNoUtil.getNo(CodePrefixCode.CODE_REQUEST_PREFIX));
        consumer.setIsBindCard(0);
        save(consumer);
        AccountRegisterDTO accountRegisterDTO = new AccountRegisterDTO();
        BeanUtils.copyProperties(consumerRegisterDTO,accountRegisterDTO);
        RestResponse<AccountDTO> restResponse = accountApiAgent.register(accountRegisterDTO);
        if (restResponse.getCode() != CommonErrorCode.SUCCESS.getCode()){
            throw new BusinessException(ConsumerErrorCode.E_140106);
        }
    }
}
