package com.wifi32767.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    public final static int SUCCESS = 200;
    public final static int ERROR = 500;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private T data;
    private int code;
    private String message;

    public Response(int code, String message) {
        this.data = null;
        this.code = code;
        this.message = message;
    }

    public Response(T data) {
        this.data = data;
        this.code = SUCCESS;
        this.message = "success";
    }

    @Override
    public String toString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            return "{}"; // 序列化失败时返回空 JSON
        }
    }
}
