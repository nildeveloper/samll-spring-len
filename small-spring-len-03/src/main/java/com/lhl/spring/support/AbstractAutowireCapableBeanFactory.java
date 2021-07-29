package com.lhl.spring.support;

import com.lhl.spring.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 20:48
 * @describe: 实例化Bean类
 * 实现createBean方法
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) {
        Object bean = null;
        try {
            // 实例化对象
             bean = createBeanInstance(beanDefinition, beanName, args);
        } catch (Exception e) {
            throw new RuntimeException("create bean error", e);
        }
        // 注册单例表（继承过来的方法）
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 通过有参构造方法创建Bean
     * @param beanDefinition
     * @param beanName
     * @param args
     * @return
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object... args) {
        Constructor constructorToUser = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            // 根据参数个数来选定构造方法？？？
            // 还应该比对参数类型
            // 因为可能会有多个参数个数相等类型不同的重载
            if (args != null && declaredConstructor.getParameterTypes().length == args.length) {
                constructorToUser = declaredConstructor;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructorToUser, args);
    }
}
