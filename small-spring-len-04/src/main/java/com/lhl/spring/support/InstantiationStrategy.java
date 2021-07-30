package com.lhl.spring.support;

import com.lhl.spring.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-29
 * @time 14:47
 * @describe: 对象实例化策略接口
 */
public interface InstantiationStrategy {

    /**
     * 实例化对象
     * @param beanDefinition
     * @param beanName
     * @param constructor
     * @param args
     * @return
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args);
}
