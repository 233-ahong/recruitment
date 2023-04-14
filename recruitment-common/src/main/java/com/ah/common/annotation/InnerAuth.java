package com.ah.common.annotation;

import java.lang.annotation.*;

/**
 * 内部认证注解
 *
 * @author 26471
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InnerAuth {
    /**
     * 是否校验用户信息
     */
    boolean isUser() default false;
}