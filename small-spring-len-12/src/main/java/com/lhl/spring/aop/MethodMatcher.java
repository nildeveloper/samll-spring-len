package com.lhl.spring.aop;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-12
 * @time 20:37
 * @describe: 方法匹配，找到表达式范围内匹配下的目标类和方法。
 */
public interface MethodMatcher {

    /**
     * Perform static checking whether the given method matches. If this
     * @return whether or not this method matches statically
     */
    boolean matches(Method method, Class<?> targetClass);
}
