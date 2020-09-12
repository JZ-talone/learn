package com.hym.spring.learn.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/12 20:51
 */
@Component
public class OrderEventListener {

    @EventListener
    public void onCreateOrderEvent(OrderCreateEvent event) {
        System.out.printf("ApplicationListener 接口实现，订单号[%s]：,锁定商品[%s]\n",
                event.getOrder().getOrderNo(), event.getOrder().getGoods());
    }

    @EventListener
    public void onApplicationEvent(OrderUpdateEvent event) {
        System.out.printf("ApplicationListener 接口实现，订单号[%s]：,更新商品[%s]\n",
                event.getOrder().getOrderNo(), event.getOrder().getGoods());
    }
}