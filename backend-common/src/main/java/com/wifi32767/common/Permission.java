package com.wifi32767.common;

import com.wifi32767.common.enums.Module;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Permission {
    Module value();
}
