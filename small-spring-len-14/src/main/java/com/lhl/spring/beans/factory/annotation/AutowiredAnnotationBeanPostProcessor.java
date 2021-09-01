package com.lhl.spring.beans.factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import com.lhl.spring.beans.BeansException;
import com.lhl.spring.beans.PropertyValues;
import com.lhl.spring.beans.factory.BeanFactory;
import com.lhl.spring.beans.factory.BeanFactoryAware;
import com.lhl.spring.beans.factory.config.ConfigurableBeanFactory;
import com.lhl.spring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.lhl.spring.util.ClassUtils;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-31
 * @time 19:36
 * @describe: 扫描自定义注解
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableBeanFactory beanFactory;


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        // 处理注解@Value
        Class<?> clazz = bean.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        Field[] declaredFields = clazz.getDeclaredFields();
        // 属性赋值
        for (Field declaredField : declaredFields) {
            Value annotation = declaredField.getAnnotation(Value.class);
            if (annotation != null) {
                String value = annotation.value();
                value = beanFactory.resolveEmbeddedValue(value);
                BeanUtil.setFieldValue(bean, declaredField.getName(), value);
            }
        }

        // 处理Autowired注解
        for (Field field : declaredFields) {
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if (autowiredAnnotation != null) {
                Class<?> fieldType = field.getType();
                String dependentBeanName = null;
                // Autowired默认按类型注入，Qualifier指定Name注入
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                Object dependentBean = null;
                if (qualifierAnnotation != null) {
                    dependentBeanName = qualifierAnnotation.value();
                    dependentBean = beanFactory.getBean(dependentBeanName, fieldType);
                } else {
                    dependentBean = beanFactory.getBean(fieldType);
                }
                BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
            }
        }
        return pvs;
    }
}
