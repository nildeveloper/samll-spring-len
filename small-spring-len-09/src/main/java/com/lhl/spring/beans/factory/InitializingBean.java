package com.lhl.spring.beans.factory;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-03
 * @time 20:50
 * @describe: 主要用于做参数初始化
 */
public interface InitializingBean {

    /**
     * afterPropertiesSet
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;

}
