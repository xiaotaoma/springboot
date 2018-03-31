package com.mxt.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:test.properties")
public class TestConfig {
    @Value("${test.test.username}")
    public String username;
    @Value("${test.test.password}")
    public String password;
}
