package com.lhl.spring.context.event;

import com.lhl.spring.beans.BeansException;
import com.lhl.spring.beans.factory.BeanFactory;
import com.lhl.spring.beans.factory.BeanFactoryAware;
import com.lhl.spring.context.ApplicationEvent;
import com.lhl.spring.context.ApplicationListener;
import com.lhl.spring.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-11
 * @time 15:26
 * @describe:
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListenerSet = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListenerSet.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListenerSet.remove(listener);
    }


    /**
     * 获取某个Event的所有Listener
     * @param event
     * @return
     */
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListenerSet) {
            if (supportsEvent(listener, event)) {
                allListeners.add(listener);
            }
        }
        return allListeners;
    }


    /**
     * 监听器是否对该事件感兴趣
     * @param applicationListener
     * @param event
     * @return
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();

        // 按照CglibSubclassingInstantiationStrategy、 SimpleInstantiationStrategy 不同实例化类型，需判断湖区后目标class
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        // 拿到listener 接口类型
        Type genericInterface = targetClass.getGenericInterfaces()[0];
        // 泛型类型
        Type actualTypeArguments = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArguments.getTypeName();
        // Listener 泛型参数类型为Event 所以这里用反射加载Event class
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        // 判定此 eventClassName 对象所表示的类或接口与指定的 event.getClass() 参数所表示的类或接口是否相同，或是否是其超类或超接口。
        // isAssignableFrom是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，默认所有的类的终极父类都是Object。如果A.isAssignableFrom(B)结果是true，证明B可以转换成为A,也就是A可以由B转换而来。
        // isAssignableFrom 可以判断某个类是不是另一个类的父类 instanceof 可以判断某个子类的实例是否是另一个父类的子类的实例
        return eventClassName.isAssignableFrom(event.getClass());
    }

}
