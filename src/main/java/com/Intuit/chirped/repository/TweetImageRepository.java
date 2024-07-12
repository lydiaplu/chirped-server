package com.Intuit.chirped.repository;

import com.Intuit.chirped.model.TweetImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetImageRepository  extends JpaRepository<TweetImage, Integer> {
}
