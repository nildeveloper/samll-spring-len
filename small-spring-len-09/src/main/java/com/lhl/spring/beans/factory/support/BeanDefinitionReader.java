package com.lhl.spring.beans.factory.support;

import com.lhl.spring.beans.BeansException;
import com.lhl.spring.core.io.Resource;
import com.lhl.spring.core.io.ResourceLoader;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-30
 * @time 23:25
 * @describe: Simple interface for bean definition readers
 * getRegistry()、getResourceLoader()
 * 都是用于提供给后面三个方法的工具，加载和注册
 * 实现会包装到抽象类中，以免污染具体的接口实现方法。
 */
public interface BeanDefinitionReader {

    /**
     * getBeanDefinitionRegistry
     * @return
     */
    BeanDefinitionRegistry getBeanDefinitionRegistry();

    /**
     * getResourceLoader
     * @return
     */
    ResourceLoader getResourceLoader();

    /**
     * loadBeanDefinitions
     * @param resource
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * loadBeanDefinitions
     * @param resources
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;

    /**
     * loadBeanDefinitions
     * @param location
     * @throws BeansException
     */
    void loadBeanDefinitions(String location) throws BeansException;

    /**
     * loadBeanDefinitions
     * @param locations
     * @throws BeansException
     */
    void loadBeanDefinitions(String... locations) throws BeansException;

}
