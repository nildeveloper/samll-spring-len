package com.lhl.spring.beans.factory.config;

import com.lhl.spring.beans.BeansException;
import com.lhl.spring.beans.factory.BeanFactory;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-31
 * @time 17:12
 * @describe: AutowireCapableBeanFactory
 * 自动化处理Bean工厂配置
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * p
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
