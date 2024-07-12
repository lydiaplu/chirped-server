package com.Intuit.chirped.repository;

import com.Intuit.chirped.model.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface TweetRepository extends JpaRepository<Tweet, Integer> {
//    @Query("SELECT t FROM Tweet t WHERE t.user.userId = :userId OR t.user IN (SELECT f.following FROM User u JOIN u.followers f WHERE u.userId = :userId) ORDER BY COALESCE(t.updatedAt, t.createdAt) DESC")
//    Page<Tweet> findLatestTweetsByUserAndFollowers(@Param("userId") Integer userId, Pageable pageable);

    @Query("SELECT t FROM Tweet t WHERE t.user.userId = :userId " +
            "OR t.user.userId IN (SELECT f.following.userId FROM Follow f WHERE f.follower.userId = :userId) " +
            "AND (t.createdAt <= :lastSeenTimestamp OR t.updatedAt <= :lastSeenTimestamp) " +
            "ORDER BY COALESCE(t.updatedAt, t.createdAt) DESC")
    Page<Tweet> findLatestTweetsByUserAndFollowers(
            @Param("userId") Integer userId,
            @Param("lastSeenTimestamp") Date lastSeenTimestamp,
            Pageable pageable
    );


    @Query("SELECT t FROM Tweet t WHERE t.user.userId = :userId " +
            "AND (t.createdAt <= :lastSeenTimestamp OR t.updatedAt <= :lastSeenTimestamp) " +
            "ORDER BY COALESCE(t.updatedAt, t.createdAt) DESC")
    Page<Tweet> findLatestTweetsByUser(
            @Param("userId") Integer userId,
            @Param("lastSeenTimestamp") Date lastSeenTimestamp,
            Pageable pageable
    );

    @Query("SELECT t FROM Tweet t WHERE t.user.username = :username " +
            "AND (t.createdAt <= :lastSeenTimestamp OR t.updatedAt <= :lastSeenTimestamp) " +
            "ORDER BY COALESCE(t.updatedAt, t.createdAt) DESC")
    Page<Tweet> findLatestTweetsByUsername(
            @Param("username") String username,
            @Param("lastSeenTimestamp") Date lastSeenTimestamp,
            Pageable pageable
    );

    @Query("SELECT t FROM Tweet t LEFT JOIN FETCH t.comments c LEFT JOIN FETCH c.replies WHERE t.tweetId = :tweetId")
    Tweet findTweetWithComments(@Param("tweetId") Integer tweetId);
}
