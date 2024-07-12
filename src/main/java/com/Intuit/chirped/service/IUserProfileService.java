package com.Intuit.chirped.service;

import com.Intuit.chirped.model.UserProfile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public interface IUserProfileService {
    UserProfile addUserProfile(String email, String displayName, String password, String bio, MultipartFile profilePic) throws SQLException, IOException;

    public UserProfile updateUserProfile(Integer userId, String displayName, String password, String bio, MultipartFile profilePic) throws SQLException;

    Optional<UserProfile> getUserProfileByUserId(Integer userId);
}
