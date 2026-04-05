package com.wifi32767.interfaces.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {
    public final static int SUCCESS = 200;
    public final static int ERROR = 500;
    private T body;
    private int code;
    private String message;

    public Response(int code, String message) {
        this.body = null;
        this.code = code;
        this.message = message;
    }

    public Response(T body) {
        this.body = body;
        this.code = SUCCESS;
        this.message = "success";
    }
}
