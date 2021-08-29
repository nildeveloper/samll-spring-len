package com.lhl.spring.context.event;

import com.lhl.spring.context.ApplicationEvent;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-11
 * @time 14:54
 * @describe:
 */
public class ContextRefreshedEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
