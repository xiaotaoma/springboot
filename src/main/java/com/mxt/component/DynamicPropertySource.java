package com.mxt.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.MapPropertySource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DynamicPropertySource extends MapPropertySource {

    private static ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);

    public DynamicPropertySource(String name) {
        super(name, map);
    }
    private static final Logger logger = LoggerFactory.getLogger(DynamicPropertySource.class);
    private static Map<String, Object> map = new ConcurrentHashMap<String, Object>(64);
    static {
        scheduled.scheduleAtFixedRate(()-> {

        }, 1, 10 , TimeUnit.SECONDS);
    }

    //动态获取资源信息
    private static Map<String, Object> dynamicLoadMapInfo() {
        //通过http或tcp等通信协议获取配置信息
        return mockMapInfo();
    }

    private static Map<String, Object> mockMapInfo() {
        Map<String, Object> map = new HashMap<String, Object>();
        int randomData = new Random().nextInt();
        logger.info("random data{};currentTime:{}", randomData, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        map.put("dynamic-info", randomData);
        return map;
    }

    @Override
    public Object getProperty(String name) {
        return map.get(name);
    }
}
