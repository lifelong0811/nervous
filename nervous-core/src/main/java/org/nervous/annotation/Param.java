package org.nervous.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Param注解;
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Param {

    /**
     * param的名字;
     * 如果期望被复用，必须起名字;
     */
    String name();

    /**
     * param要映射到的module的名字;
     */
    String mapperParamName();


    /**
     * param要映射到具体类成员路径;
     * 可以为空;
     */
    String mapperMemberName();
}
