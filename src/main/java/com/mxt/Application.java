package com.mxt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by mxt on 18-2-5.
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan({"com.mxt.dao"})/** 扫描Mapper文件，使用此注解不需要在Mapper文件中加入@Mapper注解  **/
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
