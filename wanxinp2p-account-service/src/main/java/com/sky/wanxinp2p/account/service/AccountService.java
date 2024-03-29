package com.sky.wanxinp2p.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.wanxinp2p.account.entity.Account;
import com.sky.wanxinp2p.account.model.AccountDTO;
import com.sky.wanxinp2p.account.model.AccountLoginDTO;
import com.sky.wanxinp2p.account.model.AccountRegisterDTO;
import com.sky.wanxinp2p.common.domain.RestResponse;

public interface AccountService extends IService<Account> {

    /**
     * 获取手机验证码
     * @param mobile 手机号
     * @return
     */
    RestResponse getSMSCode(String mobile);


    /**
     * 校验手机号和验证码
     * @param mobile
     * @param key
     * @param code
     * @return
     */
    Integer checkMobile(String mobile, String key, String code);

    /**
     * 账户注册
     * @param registerDTO 注册信息
     * @return
     */
    AccountDTO register(AccountRegisterDTO registerDTO);

    /**
     登录功能
     @param accountLoginDTO 封装登录请求数据
     @return 用户及权限信息
     */
    AccountDTO login(AccountLoginDTO accountLoginDTO);
}
