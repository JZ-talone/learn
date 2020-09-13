package com.hym.spring.learn;

import com.hym.spring.learn.mongo.User;
import com.hym.spring.learn.mongo.UserRepositoryImpl;
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
public class UserDaoTest {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setId(2l);
        user.setUserName("小明");
        user.setPassWord("fffooo123");
        userRepository.saveUser(user);

    }

    @Test
    public void findUserByUserName() {
        User user = userRepository.findUserByUserName("小明");
        System.out.println("user is " + user);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(2l);
        user.setUserName("天空");
        user.setPassWord("fffxxxx");
        userRepository.updateUser(user);
    }

    @Test
    public void deleteUserById() {
        userRepository.deleteUserById(1l);
    }

}
