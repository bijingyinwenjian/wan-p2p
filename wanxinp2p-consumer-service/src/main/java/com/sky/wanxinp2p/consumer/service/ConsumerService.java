package com.sky.wanxinp2p.consumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.wanxinp2p.consumer.entity.Consumer;
import com.sky.wanxinp2p.consumer.model.ConsumerRegisterDTO;

public interface ConsumerService extends IService<Consumer> {

    /**
     * 检测用户是否存在
     * @param mobile
     * @return
     */
    Integer checkMobile(String mobile);
    /**
     * 用户注册
     * @param consumerRegisterDTO
     * @return
     */
    void register(ConsumerRegisterDTO consumerRegisterDTO);
}
