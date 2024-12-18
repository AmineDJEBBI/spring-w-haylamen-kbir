package com.example.managementstore.auth;

import com.example.managementstore.entities.Role;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private Role.RoleType role;

    private String firstname;
    private String lastname;
    private String email;
    private String password;

}


