package com.wifi32767.interfaces.common;

import com.wifi32767.infra.dao.po.User;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Permission {
    public static final String ADMIN = User.ADMIN;
    public static final String AUDITOR = User.AUDITOR;
    public static final String TYPIST = User.TYPIST;
    public static final String USER = User.USER;

    String value() default USER;
}
