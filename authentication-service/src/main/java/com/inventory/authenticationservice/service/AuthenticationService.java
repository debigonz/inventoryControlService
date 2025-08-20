package com.inventory.authenticationservice.service;

import com.inventory.authenticationservice.client.UserServiceClient;
import com.inventory.authenticationservice.domain.model.AuthRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationService {

    private final UserServiceClient userServiceClient;

    public boolean authenticate(String email, String password) {
        try {
            userServiceClient.validateCredentials(new AuthRequest(email, password));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
