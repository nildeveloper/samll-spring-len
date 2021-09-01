package com.lhl.spring.aop;

import org.aopalliance.aop.Advice;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-16
 * @time 19:35
 * @describe: 定义Advice拦截器链
 * Advice 都是通过方法拦截器 MethodInterceptor 实现的。
 * 环绕 Advice 类似一个拦截器的链路，Before Advice、After advice等
 */
public interface BeforeAdvice extends Advice {
}
