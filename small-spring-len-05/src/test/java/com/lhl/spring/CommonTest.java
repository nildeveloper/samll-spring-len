package com.lhl.spring;

import cn.hutool.core.io.IoUtil;
import com.lhl.spring.beans.factory.support.DefaultListableBeanFactory;
import com.lhl.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.lhl.spring.core.io.DefaultResourceLoader;
import com.lhl.spring.core.io.Resource;
import com.lhl.spring.main.UserService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-30
 * @time 14:28
 * @describe:
 */
public class CommonTest {

    private DefaultResourceLoader resourceLoader;

    @Before
    public void initialize() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void classpathTest() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:application.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void fileSystemTest() throws IOException {
        Resource resource = resourceLoader.getResource("/Users/haoluliu/workspace/small-spring-len/small-spring-len-05/src/main/resources/application.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void urlTest() throws IOException {
//        Resource resource = resourceLoader.getResource("https://github.com/nildeveloper/microservicecloud-config/blob/master/application.yml");
        Resource resource = resourceLoader.getResource("https://www.baidu.com/");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void xmlTest() {
        // 初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 读取配置文件注册Bean
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
        
        // 获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
