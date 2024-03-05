package com.sky.wanxinp2p.account.controller;

import com.sky.wanxinp2p.account.AccountAPI;
import com.sky.wanxinp2p.account.service.AccountService;
import com.sky.wanxinp2p.common.domain.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@Api(value = "统一账号服务", tags = "Account", description = "统一账号服务API")
public class AccountController implements AccountAPI {

    @Resource
    private AccountService accountService;

    @Override
    @ApiOperation("获取手机验证码")
    @ApiImplicitParam(name = "mobile", value = "手机号", dataType = "String")
    @GetMapping("/sms/{mobile}")
    public RestResponse getSMSCode(String mobile) {
        return accountService.getSMSCode(mobile);
    }
}
