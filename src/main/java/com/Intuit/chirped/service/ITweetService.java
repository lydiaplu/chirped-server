package com.Intuit.chirped.service;

import com.Intuit.chirped.model.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITweetService {
    public Tweet addTweet(Integer userId, String content, MultipartFile[] images) throws IOException;

    public Tweet updateTweet(Integer tweetId, String content, List<Integer> imageIdsToDelete, MultipartFile[] newImages) throws IOException;

    public void deleteTweet(Integer tweetId);

    public Page<Tweet> findLatestTweetsByUserAndFollowers(Integer userId, Date lastSeenTimestamp, Pageable pageable);

    public Page<Tweet> findLatestTweetsByUser(Integer userId, Date lastSeenTimestamp, Pageable pageable);

    public Page<Tweet> findLatestTweetsByUsername(String username, Date lastSeenTimestamp, Pageable pageable);

    public Optional<Tweet> getTweetById(Integer userId);
}
