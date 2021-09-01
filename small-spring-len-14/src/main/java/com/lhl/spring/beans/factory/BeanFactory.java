package com.lhl.spring.beans.factory;


import com.lhl.spring.beans.BeansException;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 20:38
 * @describe:
 */
public interface BeanFactory {

    /**
     * getBean
     * @param name
     * @return
     */
    Object getBean(String name);

    /**
     * getBean
     * 带args参数，可使用有参构造方法初始化对象实例
     * @param name
     * @param args
     * @return
     */
    Object getBean(String name, Object... args);

    /**
     * 根据类型获取Bean
     * @param name
     * @param requestedType
     * @param <T>
     * @return
     */
    <T> T getBean(String name, Class<T> requestedType);


    <T> T getBean(Class<T> requiredType) throws BeansException;
}

