package com.wifi32767.interfaces.common;

import com.wifi32767.domain.user.model.SimpleUserVO;

public class UserContext {

    // 使用静态 ThreadLocal 存储用户信息
    private static final ThreadLocal<SimpleUserVO> USER_HOLDER = new ThreadLocal<>();

    // 防止实例化
    private UserContext() {
    }

    public static void set(SimpleUserVO user) {
        USER_HOLDER.set(user);
    }

    public static SimpleUserVO get() {
        return USER_HOLDER.get();
    }

    /**
     * 必须调用！防止内存泄漏
     */
    public static void remove() {
        USER_HOLDER.remove();
    }
}