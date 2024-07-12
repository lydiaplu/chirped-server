package com.Intuit.chirped.controller;

import com.Intuit.chirped.exception.ResourceNotFoundException;
import com.Intuit.chirped.model.UserProfile;
import com.Intuit.chirped.response.UserProfileResponse;
import com.Intuit.chirped.response.UserResponse;
import com.Intuit.chirped.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-profiles")
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping("/add/new-user-profile")
    public ResponseEntity<UserProfileResponse> addUserProfile(
            @RequestParam String email,
            @RequestParam String displayName,
            @RequestParam String password,
            @RequestParam String bio,
            @RequestParam(required = false) MultipartFile profilePic
    ) throws SQLException, IOException {
        UserProfile userProfile = userProfileService.addUserProfile(email, displayName, password, bio, profilePic);
        UserProfileResponse userProfileResponse = getUserProfileResponse(userProfile);
        return ResponseEntity.ok(userProfileResponse);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserProfileResponse> updateUserProfile(
            @PathVariable Integer userId,
            @RequestParam String displayName,
            @RequestParam String password,
            @RequestParam String bio,
            @RequestParam(required = false) MultipartFile profilePic
    ) throws SQLException {
        UserProfile userProfile = userProfileService.updateUserProfile(userId, displayName, password, bio, profilePic);
        UserProfileResponse userProfileResponse = getUserProfileResponse(userProfile);
        return ResponseEntity.ok(userProfileResponse);
    }

    @GetMapping("/user-profile/{userId}")
    public ResponseEntity<Optional<UserProfileResponse>> getUserProfileById(@PathVariable Integer userId) {
        Optional<UserProfile> theUserProfile = userProfileService.getUserProfileByUserId(userId);

        ResponseEntity<Optional<UserProfileResponse>> response = theUserProfile.map(userProfile -> {
            UserProfileResponse userProfileResponse = getUserProfileResponse(userProfile);
            return ResponseEntity.ok(Optional.of(userProfileResponse));
        }).orElseThrow(() -> new ResourceNotFoundException("userProfile not found"));

        return response;
    }

    public static UserProfileResponse getUserProfileResponse(UserProfile userProfile) {
        UserResponse theUserResponse = UserController.getUserResponse(userProfile.getUser());

        return new UserProfileResponse(
                userProfile.getUserId(),
                userProfile.getDisplayName(),
                userProfile.getPassword(),
                userProfile.getBio(),
                userProfile.getProfilePicUrl(),
                userProfile.getCreatedAt(),
                userProfile.getUpdatedAt(),
                theUserResponse
        );
    }
}
