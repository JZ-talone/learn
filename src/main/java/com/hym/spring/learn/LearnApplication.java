package com.hym.spring.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableScheduling
public class LearnApplication {
    public static void main(String[] args) {
        System.out.println("------before");
        SpringApplication.run(LearnApplication.class, args);
        System.out.println("------after");
    }
}
