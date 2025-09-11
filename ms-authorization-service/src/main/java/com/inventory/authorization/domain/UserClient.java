package com.inventory.authorization.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserClient {

    private String username;
    private String password;
    private String email;
    private UserRoleClient role;
}
