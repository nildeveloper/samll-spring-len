package com.lhl.spring.beans;

import com.lhl.spring.beans.factory.ConfigurableListableBeanFactory;
import com.lhl.spring.beans.factory.config.BeanDefinition;
import com.lhl.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.lhl.spring.core.io.DefaultResourceLoader;
import com.lhl.spring.core.io.Resource;

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
                    String strValue = (String)value;
                    // 找出占位符位置
                    StringBuilder builder = new StringBuilder(strValue);
                    int startIdx = builder.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int endIdx = builder.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    if (startIdx != -1 && endIdx != -1 && startIdx < endIdx) {
                        // 解析占位符key 2 = ${
                        String propKey = strValue.substring(startIdx + 2, endIdx);
                        String propValue = properties.getProperty(propKey);
                        builder.replace(startIdx, endIdx + 1, propValue);
                        // 属性占位符解析后替换
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), builder.toString()));
                    }
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
}
