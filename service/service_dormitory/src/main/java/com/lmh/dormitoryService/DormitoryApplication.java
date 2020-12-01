package com.lmh.dormitoryService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients//服务调用
@EnableDiscoveryClient//nacos注册
@MapperScan("com.lmh.dormitoryService.mapper")
@ComponentScan(basePackages = {"com.lmh"})
public class DormitoryApplication {
    public static void main(String[] args) {
        //
        SpringApplication.run(DormitoryApplication.class,args);
    }
}
