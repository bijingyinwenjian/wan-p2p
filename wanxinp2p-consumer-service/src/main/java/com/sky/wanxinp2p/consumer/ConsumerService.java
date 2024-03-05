package com.sky.wanxinp2p.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerService {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerService.class, args);
    }
}
