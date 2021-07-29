package com.lhl.spring;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 20:06
 * @describe: 一个BeanDefinition
 * 包含一个Object来存放对象
 * 定义bean实例化信息
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
