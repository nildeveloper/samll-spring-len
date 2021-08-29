package com.lhl.spring.context.support;

import com.lhl.spring.beans.BeansException;
import com.lhl.spring.beans.factory.config.BeanPostProcessor;
import com.lhl.spring.context.ApplicationContext;
import com.lhl.spring.context.ApplicationContextAware;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-06
 * @time 16:01
 * @describe:
 * 由于 ApplicationContext 的获取并不能直接在创建 Bean 时候就可以拿到，
 * 所以需要在 refresh 操作时，把 ApplicationContext 写入到一个包装的
 * BeanPostProcessor 中去，再由 AbstractAutowireCapableBeanFactory.
 * applyBeanPostProcessorsBeforeInitialization 方法调用
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
