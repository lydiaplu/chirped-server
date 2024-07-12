package com.Intuit.chirped.service;

import com.Intuit.chirped.model.User;
import com.Intuit.chirped.model.UserProfile;
import com.Intuit.chirped.repository.UserProfileRepository;
import com.Intuit.chirped.request.UserProfileRequest;
import com.Intuit.chirped.utils.FileStorageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService implements IUserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserService userService;
    private final FileStorageService fileStorageService;

    @Override
    public UserProfile addUserProfile(String email, String displayName, String password, String bio, MultipartFile profilePic)
            throws SQLException, IOException {
        User theUser = userService.getUserByEmail(email);

        UserProfile userProfile = new UserProfile();
        userProfile.setUser(theUser);
        userProfile.setDisplayName(displayName);
        userProfile.setPassword(password);
        userProfile.setBio(bio);
        userProfile.setCreatedAt();

        if (profilePic != null) {
            String folder = "profiles/" + theUser.getUserId();
            String fileUrl = fileStorageService.storeFile(profilePic, folder);
            userProfile.setProfilePicUrl(fileUrl);
        }

        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile updateUserProfile(Integer userId, String displayName, String password, String bio, MultipartFile profilePic)
            throws SQLException {
        UserProfile userProfile = getUserProfileByUserId(userId).get();

        userProfile.setUserId(userId);
        userProfile.setDisplayName(displayName);
        userProfile.setPassword(password);
        userProfile.setBio(bio);
        userProfile.setUpdatedAt();

        if (profilePic != null) {
            try {
                fileStorageService.deleteFile(userProfile.getProfilePicUrl());
                String folder = "profiles/" + userId;
                String fileUrl = fileStorageService.storeFile(profilePic, folder);
                userProfile.setProfilePicUrl(fileUrl);
            } catch (Exception e) {
                System.err.println("Failed to store profile picture: " + e.getMessage());
                throw new RuntimeException("Failed to update profile picture due to storage issue.");
            }
        }

        return userProfileRepository.save(userProfile);
    }

    @Override
    public Optional<UserProfile> getUserProfileByUserId(Integer userId) {
        return userProfileRepository.findById(userId);
    }

    private void setUserProfile(UserProfile userProfile, UserProfileRequest userProfileRequest) {
        User theUser = userService.getUserById(userProfileRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + userProfileRequest.getUserId()));

        userProfile.setUserId(userProfileRequest.getUserId());
        userProfile.setDisplayName(userProfileRequest.getDisplayName());
        userProfile.setPassword(userProfileRequest.getPassword());
        userProfile.setBio(userProfileRequest.getBio());
        userProfile.setProfilePicUrl(userProfileRequest.getProfilePicUrl());
        userProfile.setCreatedAt(userProfileRequest.getCreatedAt());
        if (userProfileRequest.getUpdatedAt() != null) {
            userProfile.setUpdatedAt(userProfileRequest.getUpdatedAt());
        }
    }
}
