package com.lhl.spring.support;

import cn.hutool.core.bean.BeanUtil;
import com.lhl.spring.PropertyValue;
import com.lhl.spring.PropertyValues;
import com.lhl.spring.config.BeanDefinition;
import com.lhl.spring.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 20:48
 * @describe: 实例化Bean类
 * 实现createBean方法
 * 抽象自动状态BeanFactory
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) {
        Object bean = null;
        try {
            // 实例化对象
             bean = createBeanInstance(beanDefinition, beanName, args);
             // Bean 属性填充
             applyPropertyValues(beanName, bean, beanDefinition);
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

    /**
     * Bean 属性填充
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    // A依赖B 获取B的实例
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error setting property values: " + beanName);
        }
    }
}
