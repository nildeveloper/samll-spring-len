package com.lhl.spring.beans.factory;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-06
 * @time 15:59
 * @describe: 实现此接口，既能感知到所属的 BeanName
 */
public interface BeanNameAware extends Aware{

    void setBeanName(String name);
}
