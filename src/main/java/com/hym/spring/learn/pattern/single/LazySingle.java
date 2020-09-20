package com.hym.spring.learn.pattern.single;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/20 19:53
 */
public class LazySingle {
    private static LazySingle lazySingle;

    private LazySingle() {
    }

    // synchronized 即使1.8之后还是有一定的性能问题
    public synchronized static LazySingle getInstance() {
        if (null == lazySingle) {
            lazySingle = new LazySingle();
        }
        return lazySingle;
    }

    public static void main(String[] args) {
        new Thread(new ExectorThread()).start();
        new Thread(new ExectorThread()).start();
    }

    static class ExectorThread implements Runnable {

        @Override
        public void run() {
            System.out.println(getInstance());
        }
    }
}
