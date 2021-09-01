package com.lhl.spring.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.lhl.spring.beans.factory.config.BeanDefinition;
import com.lhl.spring.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-26
 * @time 20:17
 * @describe: 处理对象扫描装配
 */
public class ClassPathScanningCandidateComponentProvider {

    /**
     * 通过配置路径解析classes信息
     * @param basePackage
     * @return
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> clazzSet = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : clazzSet) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
