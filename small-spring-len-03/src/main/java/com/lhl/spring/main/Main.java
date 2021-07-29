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

        // 注入Bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 获取bean
        UserService userService = (UserService)beanFactory.getBean("userService", "Tony");
        String user = userService.getUserById(3);
        System.out.println(user);
    }
}
