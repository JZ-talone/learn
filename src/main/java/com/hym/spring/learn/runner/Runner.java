package com.hym.spring.learn.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/16 12:22
 */
@Component
@Order(2)
public class Runner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("The Runner1 start to initialize ...");
    }
}
