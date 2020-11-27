package com.hym.spring.learn.drools.service;

import org.kie.api.runtime.KieSession;

/**
 * Created by yuhao.wang on 2017/6/19.
 */
public interface RuleService {
    /**
     * 根据ruleKey获取规则
     *
     * @param ruleKey
     * @return
     */
    KieSession getKieSessionByName(String ruleKey);

    void reloadRule();
}
