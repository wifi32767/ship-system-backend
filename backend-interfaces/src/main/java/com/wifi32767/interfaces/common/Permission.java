package com.wifi32767.interfaces.common;

import com.wifi32767.domain.common.enums.Module;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Permission {
    Module value();
}
