package com.Intuit.chirped.controller;

import com.Intuit.chirped.model.Tweet;
import com.Intuit.chirped.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {
    private final TweetService tweetService;

    @PostMapping("/add/new-tweet")
    public ResponseEntity<Tweet> addTweet(
            @RequestParam Integer userId,
            @RequestParam String content,
            @RequestParam(required = false) MultipartFile[] images) {
        try {
            Tweet tweet = tweetService.addTweet(userId, content, images);
            return ResponseEntity.ok(tweet);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update/{tweetId}")
    public ResponseEntity<Tweet> updateTweet(
            @PathVariable Integer tweetId,
            @RequestParam String content,
            @RequestParam(required = false) List<Integer> imageIdsToDelete,
            @RequestParam(required = false) MultipartFile[] images) {
        try {
            Tweet tweet = tweetService.updateTweet(tweetId, content, imageIdsToDelete, images);
            return ResponseEntity.ok(tweet);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{tweetId}")
    public ResponseEntity<Void> deleteTweet(@PathVariable Integer tweetId) {
        tweetService.deleteTweet(tweetId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/newest")
    public ResponseEntity<Page<Tweet>> getLatestTweetsByUserAndFollowers(
            @RequestParam Integer userId,
            @RequestParam Date lastSeenTimestamp,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Tweet> tweets = tweetService.findLatestTweetsByUserAndFollowers(userId,lastSeenTimestamp, pageable);

        return ResponseEntity.ok(tweets);
    }

    @GetMapping("/{userId}/newest")
    public ResponseEntity<Page<Tweet>> findLatestTweetsByUser(
            @PathVariable Integer userId,
            @RequestParam Date lastSeenTimestamp,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Tweet> tweets = tweetService.findLatestTweetsByUser(userId, lastSeenTimestamp, pageable);

        return ResponseEntity.ok(tweets);
    }

    @GetMapping("/byusername/{username}/newest")
    public ResponseEntity<Page<Tweet>> findLatestTweetsByUsername(
            @PathVariable String username,
            @RequestParam Date lastSeenTimestamp,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Tweet> tweets = tweetService.findLatestTweetsByUsername(username, lastSeenTimestamp, pageable);

        return ResponseEntity.ok(tweets);
    }

    @GetMapping("/tweet/{tweetId}")
    public ResponseEntity<Optional<Tweet>> getTweetById(@PathVariable Integer tweetId) {
        Optional<Tweet> theUser = tweetService.getTweetById(tweetId);
        return ResponseEntity.ok(theUser);

//        return theUser.map(user -> {
//            UserResponse userResponse = getUserResponse(user);
//            return ResponseEntity.ok(Optional.of(userResponse));
//        }).orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }
}
