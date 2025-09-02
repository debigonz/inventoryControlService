package com.inventory.authenticationservice.client;

import com.inventory.authenticationservice.domain.AuthRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-users", url = "http://localhost:8082/users")
public interface UserServiceClient {

    @PostMapping("/validate")
    void validateCredentials(@RequestBody AuthRequest authRequest);
}
