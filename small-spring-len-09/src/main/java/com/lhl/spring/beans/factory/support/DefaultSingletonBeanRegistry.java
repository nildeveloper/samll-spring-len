package com.lhl.spring.beans.factory.support;

import com.lhl.spring.beans.BeansException;
import com.lhl.spring.beans.factory.DisposableBean;
import com.lhl.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 20:33
 * @describe:
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected static final Object NULL_OBJECT = new Object();

    /**
     * 存放单例实例
     */
    private Map<String, Object> singletonMap = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeanMap = new HashMap<>();


    /**
     * getSingleton
     * @param beanName
     * @return
     */
    @Override
    public Object getSingleton(String beanName) {
        return singletonMap.get(beanName);
    }

    /**
     * 注册单例
     * protected 可在子类中调用
     * @param beanName
     * @param singletonObject
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonMap.put(beanName, singletonObject);
    }

    public void registerDisposableBeans(String beanName, DisposableBean disposableBean) {
        disposableBeanMap.put(beanName, disposableBean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeanMap.keySet();
        Object[] disposableBeanNames = keySet.toArray();
        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeanMap.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
