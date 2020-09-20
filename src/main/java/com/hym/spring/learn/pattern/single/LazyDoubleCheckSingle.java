package com.hym.spring.learn.pattern.single;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/20 19:53
 */
public class LazyDoubleCheckSingle {
    // volatile 防止指令重排序
    private volatile static LazyDoubleCheckSingle lazySingle;

    private LazyDoubleCheckSingle() {
    }

    // 双重检查锁
    // 好处是方法不被锁
    // 但是使用了 synchronized 还是有性能问题
    public static final LazyDoubleCheckSingle getInstance() {
        if (null == lazySingle) {
            synchronized (LazyDoubleCheckSingle.class) {
                if (null == lazySingle) {
                    lazySingle = new LazyDoubleCheckSingle();
                }
            }
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
