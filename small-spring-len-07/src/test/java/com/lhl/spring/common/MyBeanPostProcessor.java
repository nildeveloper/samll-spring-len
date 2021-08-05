package com.lhl.spring.common;

import com.lhl.spring.beans.BeansException;
import com.lhl.spring.beans.factory.config.BeanPostProcessor;
import com.lhl.spring.main.UserService;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-02
 * @time 19:42
 * @describe:
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("before test");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setCompany("after test");
        }
        return bean;
    }
}
