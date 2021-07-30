package com.lhl.spring.config;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 20:31
 * @describe:
 * 单例注册接口定义
 */
public interface SingletonBeanRegistry {

    /**
     * 根据beanName获取Bean实例
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);
}
