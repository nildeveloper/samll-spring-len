package com.lhl.spring;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 20:09
 * @describe: BeanFactory
 * 能够通过getBean方法获取Bean实例
 * 通过registerBean注册Bean实例
 */
public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();


    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    public void registerBean(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }


}
