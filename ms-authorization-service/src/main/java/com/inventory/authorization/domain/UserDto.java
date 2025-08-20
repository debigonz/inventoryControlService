package com.inventory.authorization.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;

}
