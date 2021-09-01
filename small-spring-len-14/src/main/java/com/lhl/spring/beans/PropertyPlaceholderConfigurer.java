package com.lhl.spring.beans;

import com.lhl.spring.beans.factory.ConfigurableListableBeanFactory;
import com.lhl.spring.beans.factory.config.BeanDefinition;
import com.lhl.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.lhl.spring.core.io.DefaultResourceLoader;
import com.lhl.spring.core.io.Resource;
import com.lhl.spring.util.StringValueResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-26
 * @time 19:57
 * @describe: 占位符配置处理
 * 依赖于 BeanFactoryPostProcessor 在 Bean 生命周期的属性，可以在 Bean 对象实例化之前，改变属性信息
 * 完成对配置文件的加载以及摘取占位符中的在属性文件里的配置
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 加载属性文件
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                // beanDefinition
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                // propertyValue
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                // 属性填充
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) {
                        continue;
                    }
                    // 占位符属性解析
                    value = resolvePlaceholder((String) value, properties);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), value));

                    // 向容器中添加字符串解析器，供解析@Value注解使用
                    StringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(properties);
                    beanFactory.addEmbeddedValueResolver(valueResolver);
                }

            }
        } catch (IOException e) {
            throw new BeansException("could not load properties", e);
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 占位符处理
     * @param value
     * @param properties
     * @return
     */
    private String resolvePlaceholder(String value, Properties properties) {
        String strVal = value;
        StringBuilder buffer = new StringBuilder(strVal);
        int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
            String propKey = strVal.substring(startIdx + 2, stopIdx);
            String propVal = properties.getProperty(propKey);
            buffer.replace(startIdx, stopIdx + 1, propVal);
        }
        return buffer.toString();
    }


    private class PlaceholderResolvingStringValueResolver implements StringValueResolver {

        private final Properties properties;


        public PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolveStringValue(String str) {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(str, properties);
        }
    }
}
