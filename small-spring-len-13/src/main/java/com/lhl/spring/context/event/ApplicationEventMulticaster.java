package com.lhl.spring.context.event;

import com.lhl.spring.context.ApplicationEvent;
import com.lhl.spring.context.ApplicationListener;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-11
 * @time 15:17
 * @describe: 事件广播器
 */
public interface ApplicationEventMulticaster {

    /**
     *Add a listener to be notified of all events.
     * @param listener
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * Remove a listener from the notification list.
     * @param listener the listener to remove
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * Multicast the given application event to appropriate listeners.
     * @param event
     */
    void multicastEvent(ApplicationEvent event);
}
