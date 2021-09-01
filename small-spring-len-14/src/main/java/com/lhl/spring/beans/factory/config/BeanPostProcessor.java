package com.lhl.spring.beans.factory.config;

import com.lhl.spring.beans.BeansException;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-31
 * @time 17:34
 * @describe: Bean 实例化后置处理器
 */
public interface BeanPostProcessor {

    /**
     * Bean 执行初始化方法之前执行
     * @param bean
     * @param beanName
     * @return Object
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * Bean 执行初始化方法之后执行
     * @param bean
     * @param beanName
     * @return Object
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
