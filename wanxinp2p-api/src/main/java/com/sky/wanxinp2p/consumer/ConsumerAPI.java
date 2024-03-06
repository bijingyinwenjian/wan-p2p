package com.sky.wanxinp2p.consumer;

import com.sky.wanxinp2p.common.domain.RestResponse;
import com.sky.wanxinp2p.consumer.model.ConsumerRegisterDTO;

public interface ConsumerAPI {
    /**
     * 用户注册
     * @param consumerRegisterDTO
     * @return
     */
    RestResponse register(ConsumerRegisterDTO consumerRegisterDTO);
}
