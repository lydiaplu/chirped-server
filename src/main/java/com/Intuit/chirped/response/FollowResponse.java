package com.Intuit.chirped.response;

import com.Intuit.chirped.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowResponse {
    private Integer followId;
    private Date createdAt;
    private User follower;
    private User following;
}
