package com.Intuit.chirped.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileRequest {
    private Integer userId;
    private String displayName;
    private String password;
    private String bio;
    private String profilePicUrl;
    private Date createdAt;
    private Date updatedAt;
}
