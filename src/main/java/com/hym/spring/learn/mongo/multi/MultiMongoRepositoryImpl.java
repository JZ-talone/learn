package com.hym.spring.learn.mongo.multi;

import com.hym.spring.learn.mongo.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/13 12:39
 */
@Component
public class MultiMongoRepositoryImpl {

    @Resource(name = "oneMongoTemplate")
    private MongoTemplate onemongoTemplatePayment;

    @Resource(name = "twoMongoTemplate")
    private MongoTemplate twomongoTemplatePayment;

    /**
     * 创建对象
     *
     * @param user
     */
    public void saveUser(User user) {
        onemongoTemplatePayment.save(user);
        twomongoTemplatePayment.save(user);
    }

}