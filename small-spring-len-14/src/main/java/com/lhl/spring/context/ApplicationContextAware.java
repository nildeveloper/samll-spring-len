package com.lhl.spring.context;

import com.lhl.spring.beans.BeansException;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-06
 * @time 16:00
 * @describe: 实现此接口，既能感知到所属的 ApplicationContext
 */
public interface ApplicationContextAware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
