package com.lhl.spring.support;

import com.lhl.spring.config.BeanDefinition;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 20:57
 * @describe: BeanDefinition注册接口
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册BeanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
