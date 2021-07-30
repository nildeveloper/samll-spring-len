package com.lhl.spring.beans.factory.support;

import com.lhl.spring.beans.factory.config.BeanDefinition;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 20:48
 * @describe: 实例化Bean类
 * 实现createBean方法
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            // 实例化对象
            // 有构造函数入参的对象怎么处理？？？
            bean = beanDefinition.getBeanClass().newInstance();

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        // 注册单例表（继承过来的方法）
        addSingleton(beanName, bean);
        return bean;
    }
}
