package com.lhl.spring.context.support;

import com.lhl.spring.beans.factory.support.DefaultListableBeanFactory;
import com.lhl.spring.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-31
 * @time 18:37
 * @describe: AbstractXmlApplicationContext
 * 主要实现loadBeanDefinition方法 来加载所有BeanDefinitions
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null && configLocations.length > 0) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 从入口上下文类，拿到配置信息的地址描述
     * @return
     */
    protected abstract String[] getConfigLocations();
}
