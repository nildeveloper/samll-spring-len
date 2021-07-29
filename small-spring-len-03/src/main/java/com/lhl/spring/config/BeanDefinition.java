package com.lhl.spring.config;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 20:06
 * @describe:
 * 把 Bean 的实例化操作放到容器中处理
 */
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
