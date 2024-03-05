package com.sky.wanxinp2p.account.service;

import com.sky.wanxinp2p.common.domain.RestResponse;
import com.sky.wanxinp2p.common.util.OkHttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${sms.url}")
    private String smsURL;
    @Value("${sms.enable}")
    private Boolean smsEnable;

    /**
     * 获取短信验证码
     * @param mobile
     * @return
     */
    public RestResponse getSmsCode(String mobile){
        if (smsEnable){
            return OkHttpUtil.post(smsURL + "" ,"{}");
        }
        return RestResponse.success();

    }
}
