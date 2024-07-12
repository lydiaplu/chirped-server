package com.Intuit.chirped.controller;

import com.Intuit.chirped.model.Comment;
import com.Intuit.chirped.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/add/new-comment")
    public ResponseEntity<Comment> addComment(
            @RequestParam Integer tweetId,
            @RequestParam Integer userId,
            @RequestParam String content,
            @RequestParam(required = false) Integer parentCommentId) {
        try {
            Comment comment = commentService.addComment(tweetId, userId, content, parentCommentId);
            return ResponseEntity.ok(comment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/comment/{commentId}")
    public ResponseEntity<Optional<Comment>> getCommentById(@PathVariable Integer commentId) {
        Optional<Comment> theUser = commentService.getCommentById(commentId);
        return ResponseEntity.ok(theUser);
    }

    @GetMapping("/{tweetId}")
    public ResponseEntity<List<Comment>> getCommentByTweetId(@PathVariable Integer tweetId) {
        List<Comment> theUser = commentService.getCommentByTweetId(tweetId);
        return ResponseEntity.ok(theUser);
    }
}
