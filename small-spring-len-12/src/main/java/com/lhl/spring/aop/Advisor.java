package com.lhl.spring.aop;

import org.aopalliance.aop.Advice;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-16
 * @time 19:39
 * @describe: Advisor 访问者
 * Advisor 承担了 Pointcut 和 Advice 的组合，
 * Pointcut 用于获取 JoinPoint，而 Advice 决定于 JoinPoint 执行什么操作。
 */
public interface Advisor {

    /**
     * Return the advice part of this aspect. An advice may be an
     * interceptor, a before advice, a throws advice, etc.
     * @return the advice that should apply if the pointcut matches
     * @see org.aopalliance.intercept.MethodInterceptor
     * @see BeforeAdvice
     */
    Advice getAdvice();
}
