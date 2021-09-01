package com.lhl.spring.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-31
 * @time 09:44
 * @describe:
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {

    String value();
}
