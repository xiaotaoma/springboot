package com.mxt.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 定时任务配置
     */
    @Scheduled(cron="*/5 * * * * *")
    public void task() {
        System.out.println(dateFormat.format(new Date()));
    }
}
