package com.hym.spring.learn.pattern.strategy;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/21 00:42
 */
public class CashbackStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("返现");
    }
}
