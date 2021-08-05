package com.lhl.spring.beans.factory.config;

import com.lhl.spring.beans.BeansException;
import com.lhl.spring.beans.factory.ConfigurableListableBeanFactory;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-31
 * @time 17:08
 * @describe: BeanFactoryPostProcessor
 * Bean 实例化前置处理器
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有 BeanDefinition 加载完之后、实例化之前
     * 提供修改 BeanDefinition 属性的机制
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
