package com.Intuit.chirped.controller;

import com.Intuit.chirped.model.Follow;
import com.Intuit.chirped.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follows")
public class FollowController {
    private final FollowService followService;

    @PostMapping("/add/new-following")
    public ResponseEntity<Follow> addFollow(@RequestParam Integer followerId, @RequestParam Integer followingId) {
        Follow follow = followService.followUser(followerId, followingId);
        return ResponseEntity.ok(follow);
    }

    @DeleteMapping("/remove/{followingId}")
    public ResponseEntity<Void> removeFollow(
            @PathVariable Integer followingId,
            @RequestParam Integer followerId
    ) {
        followService.unfollowUser(followerId, followingId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<Page<Follow>> getFollowers(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Follow> followers = followService.getFollowers(userId, pageable);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/followings/{userId}")
    public ResponseEntity<Page<Follow>> getFollowings(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Follow> following = followService.getFollowing(userId, pageable);
        return ResponseEntity.ok(following);
    }

}
