package com.lhl.spring.common;

import com.lhl.spring.beans.BeansException;
import com.lhl.spring.beans.PropertyValue;
import com.lhl.spring.beans.PropertyValues;
import com.lhl.spring.beans.factory.ConfigurableListableBeanFactory;
import com.lhl.spring.beans.factory.config.BeanDefinition;
import com.lhl.spring.beans.factory.config.BeanFactoryPostProcessor;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-02
 * @time 19:36
 * @describe:
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：58同城"));
    }
}
