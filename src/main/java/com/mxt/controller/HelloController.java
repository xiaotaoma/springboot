package com.mxt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mxt on 18-2-5.
 */
@RestController
public class HelloController {

    /*@RequestMapping("/")
    public String index() {
        System.out.println("index");
        return "com/mxt/controller";
    }*/

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
}
