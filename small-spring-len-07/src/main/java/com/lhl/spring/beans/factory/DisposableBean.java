package com.lhl.spring.beans.factory;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-03
 * @time 20:51
 * @describe: 销毁相关工作
 */
public interface DisposableBean {

    /**
     * destroy
     * @throws Exception
     */
    void destroy() throws Exception;
}
