package com.hym.spring.learn.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/20 23:35
 */
public class JDKDynamicProxy implements InvocationHandler {

    private Object obj;

    public Object getInstance(Object proxyobj) {
        this.obj = proxyobj;
        Class<?> clazz = proxyobj.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(proxy);
        Object obj = method.invoke(proxy, args);
        after(proxy);
        return obj;
    }

    /**
     * @param proxy
     */
    private void after(Object proxy) {
        System.out.println("proxy before");
    }

    private void before(Object proxy) {
        System.out.println("proxy after");
    }
}
