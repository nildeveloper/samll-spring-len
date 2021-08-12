package com.lhl.spring.beans.factory;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-06
 * @time 15:58
 * @describe: 实现此接口 即能感知到所属的ClassLoader
 */
public interface BeanClassLoaderAware extends Aware{

    void setBeanClassLoader(ClassLoader classLoader);
}
