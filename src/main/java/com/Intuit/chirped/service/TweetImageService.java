package com.Intuit.chirped.service;

import com.Intuit.chirped.model.Tweet;
import com.Intuit.chirped.model.TweetImage;
import com.Intuit.chirped.repository.TweetImageRepository;
import com.Intuit.chirped.utils.FileStorageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class TweetImageService implements ITweetImageService {
    private final FileStorageService fileStorageService;
    private final TweetImageRepository tweetImageRepository;

    @Override
    @Transactional
    public void addTweetImage(MultipartFile file, Tweet tweet) {
        try {
            String folder = "tweet/" + tweet.getUser().getUserId() + "/" + tweet.getTweetId();
            String imageUrl = fileStorageService.storeFile(file, folder);

            TweetImage tweetImage = new TweetImage();
            tweetImage.setImageUrl(imageUrl);
            tweetImage.setTweet(tweet);

            tweetImageRepository.save(tweetImage);
        } catch (IOException e) {
            System.err.println("Failed to store image file: " + e.getMessage());
            throw new RuntimeException("Failed to store image file.", e);
        } catch (DataAccessException e) {
            System.err.println("Database access error when saving tweet image: " + e.getMessage());
            throw new RuntimeException("Failed to save tweet image to the database.", e);
        } catch (Exception e) {
            // 记录其他类型的异常
            System.err.println("An unexpected error occurred: " + e.getMessage());
            throw new RuntimeException("An unexpected error occurred while adding a tweet image.", e);
        }
    }

    @Override
    @Transactional
    public void deleteTweetImage(Integer imageId) {
        try {
            TweetImage image = tweetImageRepository.findById(imageId)
                    .orElseThrow(() -> new EntityNotFoundException("Image not found with id: " + imageId));

            fileStorageService.deleteFile(image.getImageUrl());
            tweetImageRepository.delete(image);
        } catch (EntityNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException("Image not found and cannot be deleted.");
        } catch (Exception e) {
            System.err.println("Failed to delete image file: " + e.getMessage());
            throw new RuntimeException("Failed to delete image file due to system error.");
        }
    }
}
