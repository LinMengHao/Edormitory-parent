package com.lmh.adminService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@MapperScan("com.lmh.adminService.mapper")
@ComponentScan(basePackages = {"com.lmh"})
public class AdminServiceApplication {
  public static void main(String[] args) {
    //
      SpringApplication.run(AdminServiceApplication.class,args);
  }
}
