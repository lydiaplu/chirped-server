package com.Intuit.chirped.service;

import com.Intuit.chirped.model.Comment;
import com.Intuit.chirped.model.Tweet;
import com.Intuit.chirped.model.User;
import com.Intuit.chirped.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final TweetService tweetService;

    @Override
    public Comment addComment(Integer tweetId, Integer userId, String content, Integer parentCommentId) {
        Tweet theTweet = tweetService.getTweetById(tweetId).get();
        User theUser = userService.getUserById(userId).get();

        Comment comment = new Comment();
        comment.setTweet(theTweet);
        comment.setUser(theUser);
        comment.setContent(content);
        comment.setCreatedAt(new Date());

        if (parentCommentId != null) {
            Comment theComment = getCommentById(parentCommentId).get();
            comment.setParentComment(theComment);
        }

        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Optional<Comment> theComment = getCommentById(commentId);
        if(theComment.isPresent()) {
            commentRepository.deleteById(commentId);
        }
    }

    @Override
    public Optional<Comment> getCommentById(Integer commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    public List<Comment> getCommentByTweetId(Integer tweetId) {
        return commentRepository.findByTweetId(tweetId);
    }
}
