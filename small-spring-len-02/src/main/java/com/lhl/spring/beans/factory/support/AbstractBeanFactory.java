package com.lhl.spring.beans.factory.support;

import com.lhl.spring.BeanFactory;
import com.lhl.spring.beans.factory.config.BeanDefinition;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 20:36
 * @describe: 模板
 * 这里使用继承获得使用单例注册类的方法  为什么不使用组合？？？
 *
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {


    @Override
    public Object getBean(String name) {
        // 查询单例
        Object singleton = getSingleton(name);
        if (singleton != null) {
            return singleton;
        }
        // 单例表内不存在则创建
        // 如何创建 延迟到子类实现
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    /**
     * getBeanDefinition
     * @param beanName
     * @return
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);

    /**
     * createBean
     * @param beanName
     * @param beanDefinition
     * @return
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);


}
