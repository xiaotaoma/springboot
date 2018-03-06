package com.mxt.controller;

import com.mxt.model.Test;
import com.mxt.service.TestService;
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
    @Autowired
    private TestService testService;

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
        return list;
    }
    @RequestMapping("/test1")
    @ResponseBody
    public Object userLogin1() {
        List<Test> list = testService.list1();
        return list;
    }
}
