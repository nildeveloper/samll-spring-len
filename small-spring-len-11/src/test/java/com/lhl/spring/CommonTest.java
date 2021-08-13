package com.lhl.spring;

import cn.hutool.core.io.IoUtil;
import com.lhl.spring.aop.AdvisedSupport;
import com.lhl.spring.aop.MethodMatcher;
import com.lhl.spring.aop.TargetSource;
import com.lhl.spring.aop.aspectj.AspectJExpressionPointcut;
import com.lhl.spring.aop.framework.Cglib2AopProxy;
import com.lhl.spring.aop.framework.JdkDynamicAopProxy;
import com.lhl.spring.aop.framework.ReflectiveMethodInvocation;
import com.lhl.spring.beans.factory.support.DefaultListableBeanFactory;
import com.lhl.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.lhl.spring.common.CustomEvent;
import com.lhl.spring.common.MyBeanFactoryPostProcessor;
import com.lhl.spring.common.MyBeanPostProcessor;
import com.lhl.spring.common.UserServiceInterceptor;
import com.lhl.spring.context.support.ClassPathXmlApplicationContext;
import com.lhl.spring.core.io.DefaultResourceLoader;
import com.lhl.spring.core.io.Resource;
import com.lhl.spring.main.IUserService;
import com.lhl.spring.main.UserService;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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

    @Test
    public void processorTest() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
        MyBeanFactoryPostProcessor myBeanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        myBeanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // Bean实例化之后，修改 Bean 属性信息
        MyBeanPostProcessor myBeanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(myBeanPostProcessor);

        // 获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    @Test
    public void classPathXmlApplicationContextTest() {
        // 初始化BeanFactory
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = classPathXmlApplicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void initAndDestroyTest() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        classPathXmlApplicationContext.registerShutDownHook();
        UserService userService = classPathXmlApplicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

//    @Test
//    public void awareTest() {
//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
//        classPathXmlApplicationContext.registerShutDownHook();
//
//        UserService userService = classPathXmlApplicationContext.getBean("userService", UserService.class);
//        userService.queryUserInfo();
//        System.out.println("ApplicationContextAware: " + userService.getApplicationContext());
//        System.out.println("BeanFactoryAware: " + userService.getBeanFactory());
//    }

    @Test
    public void scopeTest() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutDownHook();

        // 2. 获取Bean对象调用方法
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
        UserService userService02 = applicationContext.getBean("userService", UserService.class);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);

        // 4. 打印十六进制哈希
        System.out.println(userService01 + " 十六进制哈希：" + Integer.toHexString(userService01.hashCode()));
//        System.out.println(ClassLayout.parseInstance(userService01).toPrintable());
    }

    @Test
    public void factoryBeanTest() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutDownHook();

        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

    @Test
    public void eventTest() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 12345, "成功了！"));
        applicationContext.registerShutDownHook();
    }

    @Test
    public void matchTest() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.lhl.spring.main.UserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }

    @Test
    public void proxyTest() {
        // 目标对象(可以替换成任何的目标对象)
        Object targetObj = new UserService();
        // AOP 代理
        IUserService proxy = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
            // 方法匹配器
            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.lhl.spring.main.IUserService.*(..))");
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodMatcher.matches(method, targetObj.getClass())) {
                    // 方法拦截器
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            System.out.println("监控 - Begin By AOP");
                            System.out.println("方法名称：" + invocation.getMethod().getName());
                            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("监控 - End\r\n");
                        }
                    };
                    // 反射调用
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
                }
                return method.invoke(targetObj, args);
            }
        });
        String result = proxy.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    public void dynamicTest() {
        // 目标对象
        IUserService userService = new UserService();

        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.lhl.spring.main.IUserService.*(..))"));

        // 代理对象
        IUserService jdkDynamicProxy = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println(jdkDynamicProxy.queryUserInfo());

        // cglib 代理对象
        IUserService cglibProxy = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println(cglibProxy.register("456"));
    }
}
