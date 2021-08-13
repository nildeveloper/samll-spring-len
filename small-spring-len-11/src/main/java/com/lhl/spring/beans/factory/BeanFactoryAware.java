package com.lhl.spring.beans.factory;

import com.lhl.spring.beans.BeansException;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-06
 * @time 15:55
 * @describe: 实现此接口，即可感知到所属的BeanFactory
 */
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
