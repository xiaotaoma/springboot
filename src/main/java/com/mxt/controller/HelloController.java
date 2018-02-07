package com.mxt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mxt on 18-2-5.
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "com/mxt/controller";
    }
}
