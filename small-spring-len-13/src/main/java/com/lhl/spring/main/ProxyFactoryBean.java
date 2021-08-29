package com.lhl.spring.main;

import com.lhl.spring.beans.BeansException;
import com.lhl.spring.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-08
 * @time 17:41
 * @describe:
 */
public class ProxyFactoryBean implements FactoryBean<IUserDao> {


    @Override
    public IUserDao getObject() throws BeansException {
        InvocationHandler handler = (proxy, method, args) -> {
            Map<String, String> map = new HashMap<>();
            map.put("10001", "Tony");
            map.put("10002", "Jack");
            map.put("10003", "Mary");
            return "你被代理了：" + method.getName() + "：" + map.get(args[0].toString());
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
