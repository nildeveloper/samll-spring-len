package com.lhl.spring.beans.factory.support;

import com.lhl.spring.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 20:55
 * @describe:
 * 继承了 AbstractAutowireCapableBeanFactory 类，
 * 也就具备了接口 BeanFactory 和 AbstractBeanFactory 等一连串的功能实现
 * 实现了接口 BeanDefinitionRegistry 中的 registerBeanDefinition
 *
 * 可同时使用注册Bean（BeanDefinitionRegistry） 与 获取Bean（AbstractBeanFactory（模板））
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    /**
     * 容器 维护BeanDefinition
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw  new RuntimeException(String.format("% not exist", beanName));
        }
        return beanDefinition;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
