package com.lhl.spring.aop;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-16
 * @time 19:39
 * @describe:
 */
public interface PointcutAdvisor extends Advisor {

    /**
     * Get the Pointcut that drives this advisor.
     * @return
     */
    Pointcut getPointcut();
}
