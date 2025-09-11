package com.inventory.authorization.config;

import com.inventory.authorization.client.UserServiceClient;
import com.inventory.authorization.domain.UserClient;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserServiceClient userServiceClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserClient userClient = userServiceClient.getUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_" + userClient.getRole().name()));

        return org.springframework.security.core.userdetails.User.builder()
                .username(userClient.getUsername())
                .password(userClient.getPassword())
                .authorities(authorities)
                .build();
    }
}
