package com.hym.spring.learn.event;

import org.springframework.context.ApplicationEvent;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/12 20:51
 */
public class OrderUpdateEvent extends ApplicationEvent {

    private final Order order;

    public OrderUpdateEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}