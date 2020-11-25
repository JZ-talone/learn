package com.hym.spring.learn;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.hym.spring.learn.redis.RedisRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/13 21:53
 */
@SpringBootTest
public class TestRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisRepo redisRepo;

    /**
     * template 使用
     */
    @Test
    public void test() {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    /**
     * template 使用
     */
    @Test
    public void testObj() throws Exception {
        Map map = Maps.newHashMap();
        map.put("test", "aa");
        map.put("learn", 1);
        ValueOperations<String, Map> operations = redisTemplate.opsForValue();
        operations.set("com.neox", map);
        operations.set("com.neo.f", map, 1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        boolean exists = redisTemplate.hasKey("com.neo.f");
        if (exists) {
            System.out.println("exists is true");
        } else {
            System.out.println("exists is false");
        }
        Assert.assertEquals("aa", operations.get("com.neox").get("test"));
    }

    /**
     * 自动生成key 使用
     */
    @Test
    public void testAuto() {
        System.out.println(JSON.toJSON(redisRepo.getUser("111")));
        System.out.println(JSON.toJSON(redisRepo.getNewUser("111")));
    }
}