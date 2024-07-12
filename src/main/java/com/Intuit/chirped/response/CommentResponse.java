package com.Intuit.chirped.response;

import com.Intuit.chirped.model.Comment;
import com.Intuit.chirped.model.CommentImage;
import com.Intuit.chirped.model.Tweet;
import com.Intuit.chirped.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private Integer commentId;
    private String content;
    private Date createdAt;
    private Tweet tweet;
    private User user;
    private Comment parentComment;
    private List<Comment> replies;
    private List<CommentImage> images;
}
