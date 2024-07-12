package com.Intuit.chirped.repository;

import com.Intuit.chirped.model.Follow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
    @Query("SELECT f FROM Follow f WHERE f.following.userId = :followingId")
    Page<Follow> findByFollowerId(@Param("followingId") Integer followingId, Pageable pageable);

    @Query("SELECT f FROM Follow f WHERE f.follower.userId = :followerId")
    Page<Follow> findByFollowingId(@Param("followerId") Integer followerId, Pageable pageable);

    @Query("SELECT f FROM Follow f WHERE f.follower.userId = :followerId AND f.following.userId = :followingId")
    Optional<Follow> findByFollowerIdAndFollowingId(@Param("followerId") Integer followerId, @Param("followingId") Integer followingId);

}
