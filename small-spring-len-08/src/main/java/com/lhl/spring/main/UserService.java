package com.lhl.spring.main;

import com.lhl.spring.beans.BeansException;
import com.lhl.spring.beans.factory.*;
import com.lhl.spring.context.ApplicationContext;
import com.lhl.spring.context.ApplicationContextAware;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 20:15
 * @describe:
 */
public class UserService implements ApplicationContextAware, BeanFactoryAware, BeanNameAware, BeanClassLoaderAware {

    private String uid;

    private UserDao userDao;

    private String company;

    private String location;

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    public UserService() {
    }

    public UserService(String uid, UserDao userDao, String company, String location) {
        this.uid = uid;
        this.userDao = userDao;
        this.company = company;
        this.location = location;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void queryUserInfo() {
        String userName = userDao.queryUserName(uid);
        System.out.println("查询用户信息：" + userName + " " + company + " " + location);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader is " + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanName is " + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
