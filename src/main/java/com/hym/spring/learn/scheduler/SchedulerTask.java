package com.hym.spring.learn.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/13 23:25
 */
@Component
public class SchedulerTask {

    private int count = 0;

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    private void process() {
        System.out.println("this is scheduler task runing  " + (count++));
    }

}
