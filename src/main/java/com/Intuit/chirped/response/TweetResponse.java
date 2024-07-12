package com.Intuit.chirped.response;

import com.Intuit.chirped.model.Comment;
import com.Intuit.chirped.model.TweetImage;
import com.Intuit.chirped.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetResponse {
    private Integer tweetId;
    private Integer userId;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    private User user;
    private List<TweetImage> images;
    private List<Comment> comments;
}
