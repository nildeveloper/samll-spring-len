package com.lhl.spring.context;

import com.lhl.spring.beans.BeansException;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-31
 * @time 17:42
 * @describe: ConfigurableApplicationContext
 * 提供了 refresh 核心方法
 */
public interface ConfigurableApplicationContext extends ApplicationContext {


    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;

    /**
     * 注册虚拟机钩子
     */
    void registerShutDownHook();

    /**
     * 手动执行关闭
     */
    void close();

}
