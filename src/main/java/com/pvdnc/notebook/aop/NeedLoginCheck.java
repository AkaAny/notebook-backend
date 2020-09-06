package com.pvdnc.notebook.aop;

import com.pvdnc.notebook.entity.rest.BaseResult;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NeedLoginCheck {
    String action();
    Class<? extends BaseResult> resultType() default BaseResult.class;
}
