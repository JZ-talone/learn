package com.hym.spring.learn.observe;

import com.hym.spring.learn.event.Order;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/12 21:31
 */
public class AObserver implements Observer<Order> {
    @Override
    public void update(Order order) {
        System.out.println("it is aobserver");
    }
}
