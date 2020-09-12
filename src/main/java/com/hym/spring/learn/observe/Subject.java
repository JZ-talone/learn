package com.hym.spring.learn.observe;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2020/9/12 21:24
 */
@Getter
@Setter
public class Subject<T> {

    private T t;

    private List<Observer> observers = Lists.newArrayList();

    private int state;

    public void appendObserve(Observer Observer) {
        observers.add(Observer);
    }

    public void removeObserve(Observer Observer) {
        observers.remove(Observer);
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObserve();
    }

    private void notifyAllObserve() {
        observers.stream().forEach((observer) -> {
            observer.update(t);
        });
    }
}
