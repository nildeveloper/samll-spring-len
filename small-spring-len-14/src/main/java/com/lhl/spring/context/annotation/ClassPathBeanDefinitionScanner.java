package com.lhl.spring.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.lhl.spring.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.lhl.spring.beans.factory.config.BeanDefinition;
import com.lhl.spring.beans.factory.support.BeanDefinitionRegistry;
import com.lhl.spring.stereotype.Component;

import java.util.Set;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-26
 * @time 20:21
 * @describe:
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidateComponents = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidateComponents) {
                // 解析作用域
                String beanScope = resoleBeanScope(beanDefinition);
                if (StrUtil.isNotBlank(beanScope)) {
                    beanDefinition.setScope(beanScope);
                }
                beanDefinitionRegistry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }

        // 写这里？
        // 注册处理注解的 BeanPostProcessor（@Autowired、@Value）
        beanDefinitionRegistry.registerBeanDefinition("com.lhl.spring.context.annotation.internalAutowiredAnnotationProcessor", new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }

    public String resoleBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (scope != null) {
            return scope.value();
        }
        return StrUtil.EMPTY;
    }

    /**
     * beanName 默认ClassName首字母小写
     * @param beanDefinition
     * @return
     */
    public String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isNotBlank(value)) {
            value = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }
}
