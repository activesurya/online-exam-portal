package com.examination.dto;

import com.examination.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private User.UserRole role;
    private Boolean active;

    public AuthResponse(String accessToken, Long id, String email, String firstName, 
                       String lastName, User.UserRole role, Boolean active) {
        this.accessToken = accessToken;
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.active = active;
    }
}
