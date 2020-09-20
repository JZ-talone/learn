package com.hym.spring.learn.pattern.single;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/20 20:17
 * <p>
 * 没有用到synchorized
 * 懒汉
 * 因为 内部类只有在用到的时候才会加载 只会加载一次
 * 巧妙利用内部类的特性完美解决线程安全问题
 */
public class LazyInnerClassSingleton {

    private LazyInnerClassSingleton() {
        // 防止反射破坏单例
        if (null != LazyHolder.LAZY) {
            throw new RuntimeException("不允许构建多个实例");
        }
    }

    public static final LazyInnerClassSingleton getInstance() {
        return LazyHolder.LAZY;
    }

    /**
     * 防止序列化破坏单例
     * 但是还是创建了两次 只不过第一次反序列化的对象被覆盖
     *
     * @return
     */
    private Object readResolve() {
        return LazyHolder.LAZY;
    }

    private static class LazyHolder {
        private static final LazyInnerClassSingleton LAZY = new LazyInnerClassSingleton();
    }
}
