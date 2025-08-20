package com.inventory.authorization.config;

import com.inventory.authorization.client.UserServiceClient;
import com.inventory.authorization.domain.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserServiceClient userServiceClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userServiceClient.getUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().split(","))
                .build();
    }
}
