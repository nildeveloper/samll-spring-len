package com.lhl.spring.beans.factory.support;

import com.lhl.spring.beans.factory.FactoryBean;
import com.lhl.spring.beans.factory.config.BeanDefinition;
import com.lhl.spring.beans.factory.config.BeanPostProcessor;
import com.lhl.spring.beans.factory.config.ConfigurableBeanFactory;
import com.lhl.spring.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 20:36
 * @describe: 模板
 * AbstractBeanFactory
 *
 * 需要扩展出创建 FactoryBean 对象的能力
 *
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    /**
     * BeanPostProcessors to apply in createBean
     */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String name) {
        return doGetBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requestedType) {
        return (T) getBean(name);
    }

    protected<T> T doGetBean(final String name, final Object... args) {
        Object singleton = getSingleton(name);
        if (singleton != null) {
//            return (T) singleton;
            // 如果是FactoryBean 则需要调用FactoryBean的getObject方法
            return (T) getObjectForBeanInstance(singleton, name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
//        return (T) createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, name);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        // 非 FactoryBean 不处理
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }

        // FactoryBean缓存获取
        Object object = getCachedObjectForFactoryBean(beanName);

        // FactoryBean创建
        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }
        return object;
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }

    /**
     * getBeanDefinition
     * @param beanName
     * @return
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);

    /**
     * createBean
     * @param beanName
     * @param beanDefinition
     * @return
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object... args);

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

}
