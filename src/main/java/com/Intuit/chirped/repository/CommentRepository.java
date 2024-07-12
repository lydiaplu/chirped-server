package com.Intuit.chirped.repository;

import com.Intuit.chirped.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("SELECT c FROM Comment c WHERE c.tweet.tweetId = :tweetId ORDER BY c.commentId ASC")
    List<Comment> findByTweetId(@Param("tweetId") Integer tweetId);
}
