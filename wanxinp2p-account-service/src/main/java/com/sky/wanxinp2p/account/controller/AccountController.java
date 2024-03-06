package com.sky.wanxinp2p.account.controller;

import com.sky.wanxinp2p.account.AccountAPI;
import com.sky.wanxinp2p.account.model.AccountDTO;
import com.sky.wanxinp2p.account.model.AccountLoginDTO;
import com.sky.wanxinp2p.account.model.AccountRegisterDTO;
import com.sky.wanxinp2p.account.service.AccountService;
import com.sky.wanxinp2p.common.domain.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @ApiOperation("校验手机号和验证码")
    @ApiImplicitParams({@ApiImplicitParam(name = "mobile", value = "手机号", required
            = true,
            dataType = "String"),
            @ApiImplicitParam(name = "key", value = "校验标识", required = true, dataType =
                    "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, dataType =
                    "String")})
    @GetMapping(value = "/mobiles/{mobile}/key/{key}/code/{code}")
    @Override
    public RestResponse<Integer> checkMobile(String mobile, String key, String code) {
        Integer integer = accountService.checkMobile(mobile, key, code);
        return RestResponse.success(integer);
    }

    @ApiOperation("用户注册")
    @ApiImplicitParam(name = "accountRegisterDTO", value = "账户注册信息", required =
            true,
            dataType = "AccountRegisterDTO", paramType = "body")
    @PostMapping(value = "/l/accounts")
    @Override
    public RestResponse<AccountDTO> register(AccountRegisterDTO accountRegisterDTO) {
        return RestResponse.success(accountService.register(accountRegisterDTO));
    }

    @ApiOperation("用户登录")
    @ApiImplicitParam(name = "accountLoginDTO", value = "登录信息", required =
            true,
            dataType = "AccountLoginDTO", paramType
            = "body")
    @PostMapping(value = "/l/accounts/session")
    @Override
    public RestResponse<AccountDTO> login(AccountLoginDTO accountLoginDTO) {
        return null;
    }
}
