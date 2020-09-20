package com.hym.spring.learn.pattern.single;

/**
 * ${DESCRIPTION}
 * 浪费内存空间
 *
 * @author huangyiming
 * @since 2020/9/20 19:49
 */
public class HungrySingle {
    private static HungrySingle hungry;

    static {
        hungry = new HungrySingle();
    }

    private HungrySingle() {
    }

    public static HungrySingle getInstance() {
        return hungry;
    }
}
