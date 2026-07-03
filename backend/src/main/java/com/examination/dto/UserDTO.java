package com.examination.dto;

import com.examination.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private User.UserRole role;
    private Boolean active;
    private String createdAt;
    private String updatedAt;
}
