package com.Intuit.chirped.service;

import com.Intuit.chirped.model.Tweet;
import org.springframework.web.multipart.MultipartFile;

public interface ITweetImageService {
    public void addTweetImage(MultipartFile file, Tweet tweet);

    public void deleteTweetImage(Integer imageId);
}
