package com.lhl.spring.context.support;

import com.lhl.spring.beans.BeansException;
import com.lhl.spring.beans.factory.ConfigurableListableBeanFactory;
import com.lhl.spring.beans.factory.support.DefaultListableBeanFactory;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-31
 * @time 18:02
 * @describe: AbstractRefreshableApplicationContext
 * 获取Bean工厂和加载资源
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    /**
     * 加载BeanDefinitions
     * @param beanFactory
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);


}
