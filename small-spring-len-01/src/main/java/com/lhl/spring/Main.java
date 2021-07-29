package com.lhl.spring;


/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 20:16
 * @describe:
 */
public class Main {


    public static void main(String[] args) {

        // 获取工厂实例
        BeanFactory beanFactory = new BeanFactory();

        // 注册bean
        beanFactory.registerBean("userService", new BeanDefinition(new UserService()));

        // 获取bean
        UserService userService = (UserService)beanFactory.getBean("userService");

        userService.queryUserInfo();
    }
}
