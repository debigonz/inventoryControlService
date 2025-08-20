package com.inventory.authenticationservice.controller;

import com.inventory.authenticationservice.domain.model.AuthRequest;
import com.inventory.authenticationservice.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@Controller("/auth")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<Void> authenticate(@RequestBody AuthRequest authRequest) {
        boolean isAuthenticated = authenticationService.authenticate(authRequest.getUsername(), authRequest.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
