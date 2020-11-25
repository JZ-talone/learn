package com.hym.spring.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @SpringBootApplication 由以下三个注解组成
 * @Configuration
 * @ComponentScan
 * @EnableAutoConfiguration 其中 @EnableAutoConfiguration 是实现自动配置的入口，该注解又通过 @Import 注解导入了AutoConfigurationImportSelector，在该类中加载 META-INF/spring.factories 的配置信息。然后筛选出以 EnableAutoConfiguration 为 key 的数据，加载到 IOC 容器中，实现自动配置功能！
 */
@SpringBootApplication
@EnableScheduling
public class LearnApplication {
    public static void main(String[] args) {
        System.out.println("------before");
        SpringApplication.run(LearnApplication.class, args);
        System.out.println("------after");
    }
}
