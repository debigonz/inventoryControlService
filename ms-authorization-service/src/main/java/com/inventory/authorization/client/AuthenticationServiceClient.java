package com.inventory.authorization.client;

import com.inventory.authorization.domain.AuthRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-authentication-service", url = "http://localhost:8085/api/auth")
public interface AuthenticationServiceClient {

    @PostMapping("/authenticate")
    void authenticate(@RequestBody AuthRequest authRequest);
}
