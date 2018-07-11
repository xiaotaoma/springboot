package com.mxt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private static final Logger logger = LoggerFactory.getLogger(TestService.class);

    public void insert() {
        logger.info("insert");
    }

    public void update() {
        logger.info("update");
    }

    public void insertXXX() {
        logger.info("insertXXX");
    }
}
