package com.sky.wanxinp2p.consumer.controller;

import com.sky.wanxinp2p.common.domain.RestResponse;
import com.sky.wanxinp2p.consumer.ConsumerAPI;
import com.sky.wanxinp2p.consumer.model.ConsumerRegisterDTO;
import com.sky.wanxinp2p.consumer.service.ConsumerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(value = "用户服务的Controller", tags = "Consumer", description = "用户服务API")
public class ConsumerController implements ConsumerAPI {

    @Resource
    private ConsumerService consumerService;

    @ApiOperation("测试hello")
    @GetMapping(path = "/hello")
    public String hello(){
        return "hello";
    }

    @ApiOperation("测试hi")
    @ApiImplicitParam(name="name",value="姓名",required = true,dataType = "String")
    @PostMapping(value = "/hi")
    public String hi(String name){
        return "hi,"+name;
    }

    @ApiOperation("用户注册")
    @ApiImplicitParam(name = "consumerRegisterDTO", value = "用户注册", required =
            true,
            dataType = "AccountRegisterDTO", paramType = "body")
    @PostMapping(value = "/consumers")
    @Override
    public RestResponse register(ConsumerRegisterDTO consumerRegisterDTO) {
        consumerService.register(consumerRegisterDTO);
        return RestResponse.success();
    }
}
