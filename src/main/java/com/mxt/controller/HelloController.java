package com.mxt.controller;

import com.alibaba.fastjson.JSON;
import com.mxt.configuration.TestConfig;
import com.mxt.model.Test;
import com.mxt.service.RedisService;
import com.mxt.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by mxt on 18-2-5.
 */
@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private TestService testService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private TestConfig testConfig;

    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        request.getSession().setAttribute("username", username);
        try {
            response.getOutputStream().write("login".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/session")
    public void getSession(HttpServletRequest request, HttpServletResponse response) {
        Object username = request.getSession().getAttribute("username");
        try {
            response.getOutputStream().write(username == null?"".getBytes():username.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/test")
    @ResponseBody
    public Object userLogin() {
        List<Test> list = testService.list();
        logger.debug("-------------------------------------------debug");
        logger.info("-------------------------------------------info");
        logger.error("-------------------------------------------error");
        try {
            throw new RuntimeException("xxxxxxxxxxxxxxxxxxxx");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("error", e);
        }
        return list;
    }
    @RequestMapping("/test1")
    @ResponseBody
    public Object userLogin1() {
        List<Test> list = testService.list1();
        return list;
    }

    @RequestMapping("/testRedis")
    @ResponseBody
    public String testRedis() {
        /*for (int i = 0; i < 3; i++) {
            String key = "key";
            String value = "value" + i;
            redisService.setValue(i, key, value);
        }*/

        for (int i = 0; i < 3; i++) {
            String key = "key";
            System.out.println(redisService.getValue(i, key));
        }
        return "success";
    }


    @RequestMapping("/testConfig")
    @ResponseBody
    public String testConfig() {
        System.out.println(testConfig.username);
        System.out.println(testConfig.password);
        return JSON.toJSONString(testConfig);
    }
}
