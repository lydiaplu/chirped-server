package com.Intuit.chirped.service;

import com.Intuit.chirped.model.Tweet;
import com.Intuit.chirped.model.User;
import com.Intuit.chirped.repository.TweetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TweetService implements ITweetService {
    private final TweetRepository tweetRepository;
    private final TweetImageService tweetImageService;
    private final UserService userService;

    @Override
    @Transactional
    public Tweet addTweet(Integer userId, String content, MultipartFile[] images) throws IOException {
        User theUser = userService.getUserById(userId).get();

        Tweet tweet = new Tweet();
        tweet.setUser(theUser);
        tweet.setContent(content);
        tweet.setCreatedAt(new Date());
        tweet = tweetRepository.save(tweet);

        if(images != null) {
            for (MultipartFile file : images) {
                tweetImageService.addTweetImage(file, tweet);
            }
        }

        return tweet;
    }

    @Override
    @Transactional
    public Tweet updateTweet(Integer tweetId, String content, List<Integer> imageIdsToDelete, MultipartFile[] newImages) throws IOException {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new EntityNotFoundException("Tweet not found with id: " + tweetId));

        // update content
        tweet.setContent(content);
        tweet.setUpdatedAt(new Date());

        // delete image
        if (imageIdsToDelete != null) {
            for (Integer imageId : imageIdsToDelete) {
                tweetImageService.deleteTweetImage(imageId);
            }
        }

        // add new images
        if (newImages != null) {
            for (MultipartFile file : newImages) {
                tweetImageService.addTweetImage(file, tweet);
            }
        }

        return tweetRepository.save(tweet);
    }

    @Override
    public void deleteTweet(Integer tweetId){
        Optional<Tweet> theTweet = tweetRepository.findById(tweetId);
        if(theTweet.isPresent()) {
            tweetRepository.deleteById(tweetId);
        }
    }

    @Override
    public Optional<Tweet> getTweetById(Integer userId) {
        return tweetRepository.findById(userId);
    }

    @Override
    public Page<Tweet> findLatestTweetsByUserAndFollowers(Integer userId,Date lastSeenTimestamp, Pageable pageable) {
        return tweetRepository.findLatestTweetsByUserAndFollowers(userId, lastSeenTimestamp, pageable);
    }

    @Override
    public Page<Tweet> findLatestTweetsByUser(Integer userId, Date lastSeenTimestamp, Pageable pageable) {
        return tweetRepository.findLatestTweetsByUser(userId, lastSeenTimestamp, pageable);
    }

    @Override
    public Page<Tweet> findLatestTweetsByUsername(String username, Date lastSeenTimestamp, Pageable pageable) {
        return tweetRepository.findLatestTweetsByUsername(username, lastSeenTimestamp, pageable);
    }

//    @Override
//    public Tweet getTweetWithComments(Integer tweetId) {
//        Tweet tweet = tweetRepository.findTweetWithComments(tweetId);
//        TweetDTO tweetDTO = convertToDTO(tweet);
//        organizeCommentsHierarchy(tweetDTO.getComments());
//        return tweetDTO;
//    }

//    private void organizeCommentsHierarchy(List<CommentDTO> comments) {
//        Map<Integer, CommentDTO> commentMap = new HashMap<>();
//        List<CommentDTO> rootComments = new ArrayList<>();
//
//        // 将所有评论放入Map中，以评论ID为键
//        for (CommentDTO comment : comments) {
//            commentMap.put(comment.getCommentId(), comment);
//            comment.setReplies(new ArrayList<>()); // 确保回复列表被初始化
//        }
//
//        // 组织层级结构
//        for (CommentDTO comment : comments) {
//            CommentDTO parent = commentMap.get(comment.getParentCommentId());
//            if (parent != null) {
//                parent.getReplies().add(comment);
//            } else {
//                rootComments.add(comment); // 没有父评论的是根级评论
//            }
//        }
//
//        // 仅保留根级评论
//        comments.clear();
//        comments.addAll(rootComments);
//    }
}
