package com.Intuit.chirped.service;

import com.Intuit.chirped.model.Follow;
import com.Intuit.chirped.model.User;
import com.Intuit.chirped.repository.FollowRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class FollowService implements IFollowService {
    private final FollowRepository followRepository;
    private final UserService userService;

    @Override
    @Transactional
    public Follow followUser(Integer followerId, Integer followingId) {
        User follower = userService.getUserById(followerId).get();
        User following = userService.getUserById(followingId).get();

        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);
        follow.setCreatedAt(new Date());

        return followRepository.save(follow);
    }

    @Override
    @Transactional
    public void unfollowUser(Integer followerId, Integer followingId) {
        Follow follow = followRepository.findByFollowerIdAndFollowingId(followerId, followingId)
                .orElseThrow(() -> new EntityNotFoundException("Follow relationship not found"));
        followRepository.delete(follow);
    }

    @Override
    public Page<Follow> getFollowers(Integer userId, Pageable pageable) {
        return followRepository.findByFollowerId(userId, pageable);
    }

    @Override
    public Page<Follow> getFollowing(Integer userId, Pageable pageable) {
        return followRepository.findByFollowingId(userId, pageable);
    }
}