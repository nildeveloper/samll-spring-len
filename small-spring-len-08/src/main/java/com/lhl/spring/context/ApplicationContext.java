package com.lhl.spring.context;

import com.lhl.spring.beans.factory.ListableBeanFactory;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-31
 * @time 17:39
 * @describe: 应用上下文
 * ApplicationContext继承于 ListableBeanFactory
 * 也就继承了关于 BeanFactory 方法，比如一些 getBean 的方法。
 */
public interface ApplicationContext extends ListableBeanFactory {


}
