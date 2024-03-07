package com.sky.wanxinp2p.account.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.wanxinp2p.account.common.AccountErrorCode;
import com.sky.wanxinp2p.account.entity.Account;
import com.sky.wanxinp2p.account.mapper.AccountMapper;
import com.sky.wanxinp2p.account.model.AccountDTO;
import com.sky.wanxinp2p.account.model.AccountLoginDTO;
import com.sky.wanxinp2p.account.model.AccountRegisterDTO;
import com.sky.wanxinp2p.common.domain.BusinessException;
import com.sky.wanxinp2p.common.domain.RestResponse;
import com.sky.wanxinp2p.common.domain.StatusCode;
import com.sky.wanxinp2p.common.util.PasswordUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Resource
    private SmsService smsService;

    @Value("${sms.enable}")
    private Boolean smsEnable;

    /**
     * 获取手机验证码
     * @param mobile 手机号
     * @return
     */
    @Override
    public RestResponse getSMSCode(String mobile) {
        return smsService.getSmsCode(mobile);
    }


    /**
     * 校验手机号和验证码
     * @param mobile
     * @param key
     * @param code
     * @return
     */
    @Override
    public Integer checkMobile(String mobile, String key, String code) {
        smsService.verifySmsCode(key,code);
        Account account = getOne(Wrappers.<Account>lambdaQuery().eq(Account::getMobile, mobile));
        return account == null ? 0 : 1;
    }

    /**
     * 账户注册
     * @param registerDTO 注册信息
     * @return
     */
    @Override
    public AccountDTO register(AccountRegisterDTO registerDTO) {
        Account account = new Account();
        account.setUsername(registerDTO.getUsername());
        account.setMobile(registerDTO.getMobile());
        account.setPassword(PasswordUtil.generate(registerDTO.getPassword()));
        if(smsEnable){
            account.setPassword(PasswordUtil.generate(registerDTO.getMobile()));
        }
        account.setDomain("c");
        account.setStatus(StatusCode.STATUS_OUT.getCode());
        this.save(account);
        return convertAccountEntityToDTO(account);
    }


    /**
     登录功能
     @param accountLoginDTO 封装登录请求数据
     @return 用户及权限信息
     */
    @Override
    public AccountDTO login(AccountLoginDTO accountLoginDTO) {
        Account account = null;
        if (accountLoginDTO.getDomain().equalsIgnoreCase("c")) {
            account = getOne(Wrappers.<Account>lambdaQuery().eq(Account::getMobile, accountLoginDTO.getMobile()));
        }else{
            account = getOne(Wrappers.<Account>lambdaQuery().eq(Account::getUsername, accountLoginDTO.getUsername()));
        }
        if (account == null){
            throw new BusinessException(AccountErrorCode.E_130104);
        }
        AccountDTO accountDTO = convertAccountEntityToDTO(account);
        if (smsEnable){
            return accountDTO;
        }
        if (PasswordUtil.verify(accountLoginDTO.getPassword(),account.getPassword())){
            return accountDTO;
        }
        throw new BusinessException(AccountErrorCode.E_130105);
    }

    private AccountDTO convertAccountEntityToDTO(Account account){
        AccountDTO accountDTO = new AccountDTO();
        BeanUtils.copyProperties(accountDTO,accountDTO);
        return accountDTO;
    }
}
