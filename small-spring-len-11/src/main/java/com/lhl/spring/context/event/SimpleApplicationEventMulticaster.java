package com.lhl.spring.context.event;

import com.lhl.spring.beans.factory.BeanFactory;
import com.lhl.spring.context.ApplicationEvent;
import com.lhl.spring.context.ApplicationListener;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-12
 * @time 13:49
 * @describe:
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }


    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
