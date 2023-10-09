package com.example.shared.infrastructure;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.function.Function;

public interface DailyTaskScheduler {

    @Scheduled(cron = "0 0 * * * ?")
    void runDailyTask();


}