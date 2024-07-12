package com.Intuit.chirped.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {
    private Integer userId;
    private String displayName;
    private String password;
    private String bio;
    private String profilePicUrl;
    private Date createdAt;
    private Date updatedAt;
    private UserResponse user;
}
