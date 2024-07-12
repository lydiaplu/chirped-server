package com.Intuit.chirped.service;

import com.Intuit.chirped.model.Follow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFollowService {
    public Follow followUser(Integer followerId, Integer followingId);

    public void unfollowUser(Integer followerId, Integer followingId);

    public Page<Follow> getFollowers(Integer userId, Pageable pageable);

    public Page<Follow> getFollowing(Integer userId, Pageable pageable);

}
