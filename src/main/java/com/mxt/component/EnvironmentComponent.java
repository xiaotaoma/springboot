package com.mxt.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class EnvironmentComponent {
    @Autowired
    private Environment environment;

    public String getProValueFromEnvironment(String key) {
        String[] activeProfiles = environment.getActiveProfiles();
        System.out.println(Arrays.toString(activeProfiles));
        System.out.println(Arrays.toString(environment.getDefaultProfiles()));
        return environment.getProperty(key);
    }

    public void test() {
        System.out.println(Arrays.toString(environment.getActiveProfiles()));
        System.out.println(Arrays.toString(environment.getDefaultProfiles()));
    }


}
