package com.mxt.controller;

import com.mxt.annotation.Action;
import com.mxt.component.EnvironmentComponent;
import com.mxt.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.util.Iterator;
import java.util.Map;

@Controller
public class TestController {
    @Autowired
    private EnvironmentComponent environmentComponent;
    @Autowired
    private TestService testService;

    @Autowired
    private StandardServletEnvironment environment;


    @RequestMapping("/test")
    @ResponseBody
    public int test() {
        testService.insert();
        testService.insertXXX();
        testService.update();
        String[] activeProfiles = environment.getActiveProfiles();
        for (int i = 0; i < activeProfiles.length; i++) {
            System.out.println("profiles:"+activeProfiles[i]);
        }
        System.out.println("----------");
        String[] defaultProfiles = environment.getDefaultProfiles();
        for (int i = 0; i < defaultProfiles.length; i++) {
            System.out.println("profiles:" + defaultProfiles[i]);
        }

        System.out.println(environment.getClass());

        MutablePropertySources propertySources = environment.getPropertySources();
        Iterator<PropertySource<?>> iterator1 = propertySources.iterator();
        while (iterator1.hasNext()) {
            PropertySource<?> next = iterator1.next();
            System.out.println(next.getName() + "\t" + next.getSource());
        }

        Map<String, Object> map = environment.getSystemProperties();
        System.out.println(map.getClass());
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            System.out.println("key:" + next.getKey() + "\tvalue:" + next.getValue());
        }
        return 1;
    }

    @RequestMapping("/test1")
    @ResponseBody
    @Action("hello")
    public String test(@RequestParam("key") String key) {
        return "cccccccc";
    }
}
