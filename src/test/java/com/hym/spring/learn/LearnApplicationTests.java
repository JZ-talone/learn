package com.hym.spring.learn;

import com.hym.spring.learn.event.Order;
import com.hym.spring.learn.event.OrderService;
import com.hym.spring.learn.observe.AObserver;
import com.hym.spring.learn.observe.BObserver;
import com.hym.spring.learn.observe.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LearnApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    public void orderEventTest() {
        Order order = orderService.save(new Order());
        orderService.update(order);
    }

    @Test
    public void subjectObserverTest() {
        Subject<Order> subject = new Subject<>();
        subject.setT(new Order());
        AObserver aObserver = new AObserver();
        BObserver bObserver = new BObserver();
        subject.getObservers().add(aObserver);
        subject.getObservers().add(bObserver);
        subject.setState(2);
    }
}
