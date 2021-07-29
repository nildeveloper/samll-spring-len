package com.lhl.spring.main;

import com.lhl.spring.config.BeanDefinition;
import com.lhl.spring.support.DefaultListableBeanFactory;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 21:08
 * @describe:
 */
public class Main {

    public static void main(String[] args) {

        // 初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 第一次获取Bean
        UserService userService = (UserService)beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 第二次获取Bean
        UserService userServiceSingleton = (UserService)beanFactory.getBean("userService");
        userServiceSingleton.queryUserInfo();
    }
}
