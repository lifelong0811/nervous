package org.nervous.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * Module注解;
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Module {

    /**
     * module的名称，默认为class.getSimpleName + method.getName();
     */
    String name();

    /**
     * 前置依赖;
     * method的全路径名；
     */
    String[] dependencyOn() default {""};

    /**
     * Module请求参数生成方法;
     */
    String requestBuilder();

    /**
     * return name;
     */
    String returnName();

}
