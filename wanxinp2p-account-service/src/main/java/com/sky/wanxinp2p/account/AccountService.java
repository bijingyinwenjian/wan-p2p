package com.sky.wanxinp2p.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.sky.wanxinp2p.account.mapper")
public class AccountService {
    public static void main(String[] args) {
        SpringApplication.run(AccountService.class, args);
    }
}
