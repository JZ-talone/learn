package com.hym.spring.learn.observe;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/12 21:24
 */
public interface Observer<T> {

    void update(T t);
}
