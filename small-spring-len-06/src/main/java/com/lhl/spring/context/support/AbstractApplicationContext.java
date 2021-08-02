package com.lhl.spring.context.support;

import com.lhl.spring.beans.BeansException;
import com.lhl.spring.beans.factory.BeanFactory;
import com.lhl.spring.beans.factory.ConfigurableListableBeanFactory;
import com.lhl.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.lhl.spring.beans.factory.config.BeanPostProcessor;
import com.lhl.spring.context.ConfigurableApplicationContext;
import com.lhl.spring.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-31
 * @time 17:44
 * @describe: 应用上下文抽象类
 * 继承 DefaultResourceLoader 是为了处理 spring.xml 配置资源的加载
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {


    @Override
    public void refresh() throws BeansException {
        // 1. 创建BeanFactory并加载BeanDefinition
        refreshBeanFactory();

        // 2. 获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3. Bean实例化之前 执行全部BeanFactoryPostProcessor 扩展点
        invokeBeanFactoryPostProcessors(beanFactory);

        // 4. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 5. 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
    }

    /**
     * refreshBeanFactory
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * getBeanFactory
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 执行全部BeanFactoryPostProcessor 扩展点逻辑
     * @param beanFactory
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        // 根据 BeanFactoryPostProcessor.class 类型获取全部 BeanFactoryPostProcessor扩展
        Map<String, BeanFactoryPostProcessor> beansOfTypeMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beansOfTypeMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 注册全部 BeanPostProcessor
     * @param beanFactory
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beansOfTypeMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beansOfTypeMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public Object getBean(String name) {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requestedType) {
        return getBeanFactory().getBean(name, requestedType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }
}
