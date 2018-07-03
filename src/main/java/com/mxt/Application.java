package com.mxt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by mxt on 18-2-5.
 * SpringBootApplication
 * 注解解释：
 *   @Target({ElementType.TYPE})
     @Retention(RetentionPolicy.RUNTIME)
     @Documented
     @Inherited
     前四个注解：是元注解，用来修饰当前注解，就像public类的修饰词，没有实际功能，如果不打算写自定义注解，不需要了解
     @SpringBootConfiguration   说明这是一个配置文件类，它会被@ComponentScan扫描到
     @EnableAutoConfiguration   启动spring boot内置的自动配置
     @ComponentScan 扫描bean，路径为Application类所在package以及package下的子路径，这里为 com.lkl.springboot，在spring boot中bean都放置在该路径已经子路径下。
     (
     excludeFilters = {@Filter(
     type = FilterType.CUSTOM,
     classes = {TypeExcludeFilter.class}
     ), @Filter(
     type = FilterType.CUSTOM,
     classes = {AutoConfigurationExcludeFilter.class}
     )}
    )
 *
 *
 *
 *
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
