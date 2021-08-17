package com.lhl.spring.aop;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-16
 * @time 19:36
 * @describe:
 */
public interface MethodBeforeAdvice extends BeforeAdvice {


    /**
     * Callback before a given method is invoked.
     * @param method
     * @param args
     * @param target
     * @throws Throwable
     */
    void before(Method method, Object[] args, Object target) throws Throwable;
}
