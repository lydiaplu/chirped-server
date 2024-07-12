package com.Intuit.chirped.service;

import com.Intuit.chirped.model.Comment;

import java.util.List;
import java.util.Optional;

public interface ICommentService {
    public Comment addComment(Integer tweetId, Integer userId, String content, Integer parentCommentId);

//    public Comment updateComment(Integer commentId, Integer tweetId, Integer userId, String content, Integer parentCommentId);

    public Optional<Comment> getCommentById(Integer commentId);

    public void deleteComment(Integer commentId);

    public List<Comment> getCommentByTweetId(Integer tweetId);
}
