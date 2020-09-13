package com.hym.spring.learn.redis;

import com.google.common.collect.Maps;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/13 22:17
 */
@Component
public class RedisRepo {

    @Cacheable(value = "user-key", keyGenerator = "simpleKeyGenerator")
    public Map getUser(String name) {
        Map map = Maps.newHashMap();
        map.put("test", "aa");
        map.put("learn", 1);
        map.put("name", name);
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return map;
    }

    @Cacheable(value = "new-user-key")
    public Map getNewUser(String name) {
        Map map = Maps.newHashMap();
        map.put("name", name);
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return map;
    }
}
