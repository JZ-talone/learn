package com.hym.spring.learn.pattern.strategy;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/21 00:42
 */
public class PromotionActivity {
    PromotionStrategy promotionStrategy;

    public PromotionActivity(PromotionStrategy promotionStrategy) {
        this.promotionStrategy = promotionStrategy;
    }

    public void execute() {
        promotionStrategy.doPromotion();
    }
}
