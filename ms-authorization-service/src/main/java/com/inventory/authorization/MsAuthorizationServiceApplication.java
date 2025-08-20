package com.inventory.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsAuthorizationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsAuthorizationServiceApplication.class, args);
    }

}
