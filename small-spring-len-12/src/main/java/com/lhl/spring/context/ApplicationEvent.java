package com.lhl.spring.context;

import java.util.EventObject;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-10
 * @time 20:44
 * @describe: ApplicationEvent 具备事件功能的抽象类
 * Spring 所有事件父类
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
