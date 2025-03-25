package com.inventory.userservice.utils;

import com.inventory.userservice.domain.entity.User;
import com.inventory.userservice.domain.entity.UserRole;

public class UserFactory {

    public static User testUser(){
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setPassword("Abc123");
        user.setEmail("testUser@gmail.com");
        user.setPassword("Abc123*");
        user.setRole(UserRole.USER);
        return user;
    }

    public static User testAdmin(){
        User user = new User();
        user.setId(2L);
        user.setUsername("testAdmin");
        user.setPassword("Abc123");
        user.setEmail("testAdmin@gmail.com");
        user.setPassword("Abc123!");
        user.setRole(UserRole.ADMIN);
        return user;
    }
}
