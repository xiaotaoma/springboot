package com.mxt.configuration;

import com.mxt.component.DynamicPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
public class DynamicConfig {
    public static final String DYNAMIC_CONFIG_NAME = "dynamic_config";
    @Autowired
    private AbstractEnvironment environment;

    @PostConstruct
    public void init() {
        environment.getPropertySources().addFirst(new DynamicPropertySource(DYNAMIC_CONFIG_NAME));
    }

}
