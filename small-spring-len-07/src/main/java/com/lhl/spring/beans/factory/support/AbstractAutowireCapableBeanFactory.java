package com.lhl.spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.lhl.spring.beans.BeansException;
import com.lhl.spring.beans.PropertyValue;
import com.lhl.spring.beans.PropertyValues;
import com.lhl.spring.beans.factory.DisposableBean;
import com.lhl.spring.beans.factory.InitializingBean;
import com.lhl.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.lhl.spring.beans.factory.config.BeanDefinition;
import com.lhl.spring.beans.factory.config.BeanPostProcessor;
import com.lhl.spring.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 20:48
 * @describe: 实例化Bean类
 * 实现createBean方法
 * 在Bean创建时完成前置和后置处理
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) {
        Object bean = null;
        try {
            // 实例化对象
             bean = createBeanInstance(beanDefinition, beanName, args);
             // Bean 属性填充
             applyPropertyValues(beanName, bean, beanDefinition);

             // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new RuntimeException("create bean error", e);
        }
        // 注册销毁方法对象
        // 创建 Bean 对象的实例的时候，需要把销毁方法保存起来，方便后续执行销毁动作进行调用。
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
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

    /**
     * 初始化Bean
     * @param beanName
     * @param bean
     * @param beanDefinition
     * @return
     */
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 1. 执行BeanPostProcessors before 处理
        Object wrappedBean = applyBeanPostProcessorBeforeInitialization(bean, beanName);
        // 2. 初始化 init
        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }
        // 3. 执行BeanPostProcessors after 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    /**
     * invokeInitMethods
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        // 1. 实现接口InitializingBean接口
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }

        // 2. 配置文件配置的init-method 方法
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotBlank(initMethodName)) {
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if (initMethod == null) {
                throw new BeansException("could not find init method named " + initMethodName + "on bean with name " + beanName);
            }
            initMethod.invoke(bean);
        }
    }

    /**
     * 注册销毁方法对象
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof DisposableBean || StrUtil.isNotBlank(beanDefinition.getDestroyMethodName())) {
            registerDisposableBeans(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

    @Override
    public Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        List<BeanPostProcessor> beanPostProcessorList = getBeanPostProcessors();
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(existingBean, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        List<BeanPostProcessor> beanPostProcessorList = getBeanPostProcessors();
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            Object current = beanPostProcessor.postProcessAfterInitialization(existingBean, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }
}
