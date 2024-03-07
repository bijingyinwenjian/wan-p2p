package com.sky.wanxinp2p.account.service;

import com.alibaba.fastjson.JSON;
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
     *
     * @param mobile
     * @return
     */
    public RestResponse getSmsCode(String mobile) {
        if (smsEnable) {
            return OkHttpUtil.post(smsURL + "/generate?effectiveTime=300&name=sms", "{\"mobile\":" + mobile + "}");
        }
        return RestResponse.success();
    }

    /**
     * 校验验证码
     *
     * @param key  校验标识 redis中的键
     * @param code 短信验证码
     */
    public void verifySmsCode(String key, String code) {
        if (smsEnable) {
            RestResponse restResponse = OkHttpUtil.post(smsURL +
                            "/verify?name=sms&verificationKey=" + key + "&verificationCode=" + code,
                    "{}");
            if (restResponse.getCode() != CommonErrorCode.SUCCESS.getCode()) {
                throw new BusinessException(CommonErrorCode.E_100102);
            }
        }
    }
}
