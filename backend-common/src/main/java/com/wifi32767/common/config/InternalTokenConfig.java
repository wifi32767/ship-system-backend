package com.wifi32767.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class InternalTokenConfig {

    @Value("${token}")
    private String token;

    public String getToken() {
        return token;
    }
}