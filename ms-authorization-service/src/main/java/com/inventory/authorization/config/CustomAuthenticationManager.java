package com.inventory.authorization.config;

import com.inventory.authorization.client.AuthenticationServiceClient;
import com.inventory.authorization.domain.AuthRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private final AuthenticationServiceClient authenticationServiceClient;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {
            authenticationServiceClient.authenticate(new AuthRequest(username, password));
            return new UsernamePasswordAuthenticationToken(username, password);
        } catch (Exception e) {
            throw new AuthenticationException("Invalid username or password") {};
        }
    }
}
