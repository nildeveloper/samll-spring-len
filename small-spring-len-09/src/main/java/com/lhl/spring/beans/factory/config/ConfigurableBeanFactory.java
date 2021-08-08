package com.lhl.spring.beans.factory.config;

import com.lhl.spring.beans.factory.HierarchicalBeanFactory;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-31
 * @time 17:11
 * @describe: 可获取 BeanPostProcessor、BeanClassLoader等的一个配置化接口
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();

}
