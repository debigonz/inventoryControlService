package com.inventory.authenticationservice.service;

import com.inventory.authenticationservice.client.UserServiceClient;
import com.inventory.authenticationservice.domain.AuthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserServiceClient userServiceClient;

    public boolean authenticate(String username, String password) {
        try {
            userServiceClient.validateCredentials(new AuthRequest(username, password));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
