package com.hym.spring.learn.pattern.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/20 23:43
 */
public class CGlibProxy implements MethodInterceptor {

    public Object getInstance(Class<?> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object obj = methodProxy.invokeSuper(o, objects);
        after();
        return obj;
    }

    private void after() {
        System.out.println("proxy before");
    }

    private void before() {
        System.out.println("proxy after");
    }
}
