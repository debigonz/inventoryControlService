package com.inventory.authorization.client;

import com.inventory.authorization.domain.UserClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "ms-users", url = "http://localhost:8082/users")
public interface UserServiceClient {

    @GetMapping("/user/{email}")
    Optional<UserClient> getUserByEmail(@PathVariable String email);
}

