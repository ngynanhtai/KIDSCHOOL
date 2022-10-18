package com.example.backend.dtoresponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTORes {
    private Long id;
    private String username;
    private String password;
    private String roleName;
}
