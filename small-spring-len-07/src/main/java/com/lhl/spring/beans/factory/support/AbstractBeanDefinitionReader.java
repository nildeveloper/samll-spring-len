package com.lhl.spring.beans.factory.support;

import com.lhl.spring.core.io.DefaultResourceLoader;
import com.lhl.spring.core.io.ResourceLoader;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-30
 * @time 23:30
 * @describe: AbstractBeanDefinitionReader
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    private final BeanDefinitionRegistry definitionRegistry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry definitionRegistry, ResourceLoader resourceLoader) {
        this.definitionRegistry = definitionRegistry;
        this.resourceLoader = resourceLoader;
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry definitionRegistry) {
        this(definitionRegistry, new DefaultResourceLoader());
    }

    @Override
    public BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return definitionRegistry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
