package com.Intuit.chirped.response;

import com.Intuit.chirped.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Integer userId;
    private String username;
    private String email;
    private String department;
    private String title;
    private LocalDate startDate;
    private Date createdAt;
    private Date updatedAt;
    private User manager;
//    private List<User> subordinates;
    private Address address;
    private UserProfile userProfile;
    private List<Follow> following;
    private List<Follow> follower;
    private List<Tweet> tweets;
    private List<Comment> comments;
}
