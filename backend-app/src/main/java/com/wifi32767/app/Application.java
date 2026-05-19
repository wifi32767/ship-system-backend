package com.wifi32767.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {
        "com.wifi32767.app",
        "com.wifi32767.common",
        "com.wifi32767.domain",
        "com.wifi32767.infra",
        "com.wifi32767.interfaces"
})
@MapperScan("com.wifi32767.infra.dao")
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.wifi32767.interfaces.client")
public class Application {

    public static void main(String[] args) {
        System.out.println("Spring Boot: " + org.springframework.boot.SpringBootVersion.getVersion());
        System.out.println("Spring Framework: " + org.springframework.core.SpringVersion.getVersion());
        SpringApplication.run(Application.class);
    }

}
