package com.hym.spring.learn;

import com.hym.spring.learn.mongo.User;
import com.hym.spring.learn.mongo.multi.MultiMongoRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/13 13:08
 */
@SpringBootTest
public class MultiMongoDaoTest {

    @Autowired
    private MultiMongoRepositoryImpl multiMongoRepository;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setId(2l);
        user.setUserName("小明");
        user.setPassWord("fffooo123");
        multiMongoRepository.saveUser(user);
    }


}
