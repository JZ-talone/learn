package com.hym.spring.learn.event;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/12 20:49
 */
@Data
public class Order {
    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 商品
     */
    private String goods;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
