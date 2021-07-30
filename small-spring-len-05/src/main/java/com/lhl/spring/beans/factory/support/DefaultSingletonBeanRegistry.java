package com.lhl.spring.beans.factory.support;

import com.lhl.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 20:33
 * @describe:
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 存放单例实例
     */
    private Map<String, Object> singletonMap = new HashMap<>();


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
}
