package com.hym.spring.learn;

import com.hym.spring.learn.event.Order;
import com.hym.spring.learn.event.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LearnApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    void contextLoads() {
    }


    @Test
    public void orderEventTest() {
        Order order = orderService.save(new Order());
        orderService.update(order);
    }
}
