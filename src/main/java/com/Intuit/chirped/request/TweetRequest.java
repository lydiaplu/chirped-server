package com.Intuit.chirped.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetRequest {
    private Integer tweetId;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    private Integer userId;
}
