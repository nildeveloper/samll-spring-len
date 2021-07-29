package com.lhl.spring;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-28
 * @time 20:38
 * @describe:
 */
public interface BeanFactory {

    /**
     * getBean
     * @param name
     * @return
     */
    Object getBean(String name);
}
