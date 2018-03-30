package com.mxt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

/**
 * Created by mxt on 18-2-5.
 */
//@EnableDiscoveryClient
@SpringBootApplication
@MapperScan({"com.mxt.dao"})/** 扫描Mapper文件，使用此注解不需要在Mapper文件中加入@Mapper注解  **/
@EnableJms
@ComponentScan
@Configuration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
