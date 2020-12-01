package com.lmh.dormitoryService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.lmh.dormitoryService.mapper")
@ComponentScan(basePackages = {"com.lmh"})
public class DormitoryApplication {
    public static void main(String[] args) {
        //
        SpringApplication.run(DormitoryApplication.class,args);
    }
}
