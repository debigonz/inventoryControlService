package com.inventory.users.utils;

import com.inventory.users.domain.entity.User;
import com.inventory.users.domain.entity.UserRole;

public class UserFactory {

    public static User testUser(){
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setPassword("Abc123");
        user.setEmail("testUser@gmail.com");
        user.setRole(UserRole.USER);
        return user;
    }

    public static User testAdmin(){
        User user = new User();
        user.setId(2L);
        user.setUsername("testAdmin");
        user.setPassword("Abc123");
        user.setEmail("testAdmin@gmail.com");
        user.setRole(UserRole.ADMIN);
        return user;
    }

    public static void main(String[] args) {
        java.util.function.Function<String, String> encryptPassword = plainPassword -> {
            String encryptedPassword = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode(plainPassword);
            System.out.println("Encrypted password: " + encryptedPassword);
            return encryptedPassword;
        };
        System.out.println("Test user debi:");
        encryptPassword.apply("Test123*");
        System.out.println("Test admin santi:");
        encryptPassword.apply("Test123!");
    }
}