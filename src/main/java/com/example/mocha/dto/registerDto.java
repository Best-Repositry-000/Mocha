package com.example.mocha.dto;

import com.example.mocha.enums.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class registerDto {
    private String matricNo;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
