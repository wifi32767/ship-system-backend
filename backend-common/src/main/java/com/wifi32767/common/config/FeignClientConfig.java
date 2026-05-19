package com.wifi32767.common.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    private final InternalTokenConfig tokenConfig;

    public FeignClientConfig(InternalTokenConfig tokenConfig) {
        this.tokenConfig = tokenConfig;
    }

    @Bean
    public RequestInterceptor internalTokenInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                String token = tokenConfig.getToken();
                if (token != null && !token.isEmpty()) {
                    template.header("X-Internal-Token", token);
                }
            }
        };
    }
}