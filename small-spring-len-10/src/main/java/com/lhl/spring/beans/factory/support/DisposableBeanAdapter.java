package com.lhl.spring.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.lhl.spring.beans.factory.DisposableBean;
import com.lhl.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-05
 * @time 19:47
 * @describe: 销毁方法适配器
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 1. 实现接口DisposableBean
        if (bean instanceof DisposableBean) {
            ((DisposableBean)bean).destroy();
        }

        // 配置信息destroy-method
        if (StrUtil.isNotBlank(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (destroyMethod != null) {
                destroyMethod.invoke(bean);
            }
        }
    }
}
