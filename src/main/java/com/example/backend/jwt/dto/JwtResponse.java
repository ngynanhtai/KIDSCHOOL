package com.example.backend.jwt.dto;

import com.example.backend.dtoresponse.StudentDTORes;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse<T> {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String password;
    private List<String> roles;
    private T userInfo;

    public JwtResponse(String accessToken, Long id, String username, String password, List<String> roles, T userInfo) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.userInfo = userInfo;
    }
}
