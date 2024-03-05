package com.sky.wanxinp2p.account.service;

import com.sky.wanxinp2p.common.domain.RestResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService{

    @Resource
    private SmsService smsService;

    @Override
    public RestResponse getSMSCode(String mobile) {
        return smsService.getSmsCode(mobile);
    }
}
