package com.lhl.spring.common;

import com.lhl.spring.context.event.ApplicationContextEvent;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-12
 * @time 14:03
 * @describe:
 */
public class CustomEvent extends ApplicationContextEvent {

    private long id;

    private String message;


    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CustomEvent(Object source) {
        super(source);
    }

    public CustomEvent(Object source, long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
