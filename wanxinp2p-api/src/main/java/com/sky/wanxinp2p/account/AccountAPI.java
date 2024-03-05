package com.sky.wanxinp2p.account;

import com.sky.wanxinp2p.common.domain.RestResponse;

public interface AccountAPI {
    /**
     * 获取手机验证码
     * @param mobile 手机号
     * @return
     */
    RestResponse getSMSCode(String mobile);
}
