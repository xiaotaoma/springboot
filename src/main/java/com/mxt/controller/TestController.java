package com.mxt.controller;

import com.mxt.component.EnvironmentComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @Autowired
    private EnvironmentComponent environmentComponent;

    @RequestMapping("/test")
    @ResponseBody
    public int test() {
        environmentComponent.test();
        return 1;
    }

    @RequestMapping("/test1")
    @ResponseBody
    public String test(@RequestParam("key") String key) {
        return environmentComponent.getProValueFromEnvironment(key);
    }
}
