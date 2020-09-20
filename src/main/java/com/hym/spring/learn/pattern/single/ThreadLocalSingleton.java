package com.hym.spring.learn.pattern.single;

/**
 * ${DESCRIPTION}
 * <p>
 * 线程单例模式
 *
 * 伪线程安全
 * 使用threadlocal实现多数据源切换
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
