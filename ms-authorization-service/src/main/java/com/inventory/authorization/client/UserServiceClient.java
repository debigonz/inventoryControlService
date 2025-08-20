package com.inventory.authorization.client;

import com.inventory.authorization.domain.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-users", url = "http://localhost:8082/users")
public interface UserServiceClient {
    @GetMapping("/user/{email}")
    UserDto getUserByEmail(@PathVariable String email);
}
