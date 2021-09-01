package com.lhl.spring.aop;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-12
 * @time 20:36
 * @describe: 定义类匹配类，用于切点找到给定的接口和目标类。
 */
public interface ClassFilter {

    /**
     * Should the pointcut apply to the given interface or target class?
     * @param clazz the candidate target class
     * @return whether the advice should apply to the given target class
     */
    boolean matches(Class<?> clazz);
}
