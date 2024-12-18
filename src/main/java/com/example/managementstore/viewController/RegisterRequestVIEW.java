package com.example.managementstore.viewController;

import com.example.managementstore.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestVIEW {

    private Role.RoleType role;

    private String firstname;
    private String lastname;
    private String email;
    private String password;
}


