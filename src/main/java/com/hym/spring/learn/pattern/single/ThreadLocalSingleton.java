package com.hym.spring.learn.pattern.single;

/**
 * ${DESCRIPTION}
 * <p>
 * 线程单例模式
 *
 * @author huangyiming
 * @since 2020/9/20 21:27
 */
public class ThreadLocalSingleton {


    private static final ThreadLocal<ThreadLocalSingleton> THREAD_LOCAL_SINGLETON_THREAD_LOCAL =
            ThreadLocal.withInitial(() -> new ThreadLocalSingleton());

    private ThreadLocalSingleton() {
    }

    public static ThreadLocalSingleton getInstance() {
        return THREAD_LOCAL_SINGLETON_THREAD_LOCAL.get();
    }


}
