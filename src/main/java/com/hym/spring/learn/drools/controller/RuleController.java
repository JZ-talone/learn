package com.hym.spring.learn.drools.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rules")
public class RuleController {
    public static final Logger logger = LoggerFactory.getLogger(RuleController.class);

    //@Autowired
    //RuleService ruleService;
    //
    //@ResponseBody
    //@RequestMapping("address")
    //public Object test(int num) {
    //    AddressCheckResult result = new AddressCheckResult();
    //    Address address = new Address();
    //    address.setPostcode(generateRandom(num));
    //
    //    String ruleKey = "score";
    //    KieSession kieSession = ruleService.getKieSessionByName(ruleKey);
    //    int ruleFiredCount = 0;
    //    try {
    //        kieSession.insert(address);
    //        kieSession.insert(result);
    //        ruleFiredCount = kieSession.fireAllRules();
    //    } catch (Exception e) {
    //        logger.warn(e.getMessage(), e);
    //    } finally {
    //        if (kieSession != null) {
    //            kieSession.destroy();
    //        }
    //    }
    //    System.out.println("触发了" + ruleFiredCount + "条规则");
    //
    //    if (result.isPostCodeResult()) {
    //        System.out.println("规则校验通过");
    //    }
    //
    //    return "ok";
    //}
    //
    ///**
    // * 从数据加载最新规则
    // */
    //@ResponseBody
    //@RequestMapping("/reloadRule")
    //public String reloadRule() {
    //    ruleService.reloadRule();
    //    return "ok";
    //}
    //
    ///**
    // * 生成随机数
    // */
    //public String generateRandom(int num) {
    //    String chars = "0123456789";
    //    StringBuffer number = new StringBuffer();
    //    for (int i = 0; i < num; i++) {
    //        int rand = (int) (Math.random() * 10);
    //        number = number.append(chars.charAt(rand));
    //    }
    //    return number.toString();
    //}

}