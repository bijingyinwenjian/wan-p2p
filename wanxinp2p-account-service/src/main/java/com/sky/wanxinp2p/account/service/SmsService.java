package com.sky.wanxinp2p.account.service;

import com.sky.wanxinp2p.common.domain.BusinessException;
import com.sky.wanxinp2p.common.domain.CommonErrorCode;
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
            // TODO 发送远程验证码
            return OkHttpUtil.post(smsURL + "" ,"{}");
        }
        return RestResponse.success();
    }

    /**
     * 校验验证码
     * @param key 校验标识 redis中的键
     * @param code 短信验证码
     */
    public void verifySmsCode(String key, String code) {
        if (smsEnable){
            // TODO 校验验证码
            RestResponse restResponse = OkHttpUtil.post(smsURL + "", "{}");
            if (restResponse.getCode() != CommonErrorCode.SUCCESS.getCode()){
                throw new BusinessException(CommonErrorCode.E_100102);
            }
        }
    }
}
