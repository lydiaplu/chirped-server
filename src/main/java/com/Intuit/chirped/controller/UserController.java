package com.Intuit.chirped.controller;

import com.Intuit.chirped.exception.ResourceNotFoundException;
import com.Intuit.chirped.model.User;
import com.Intuit.chirped.response.UserResponse;
import com.Intuit.chirped.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/random")
    public ResponseEntity<List<UserResponse>> getUserByRandom(){
        List<User> users = userService.getUserByRandom();

        List<UserResponse> userResponses = new ArrayList<>();
        for(User user: users) {
            UserResponse userResponse = getUserResponse(user);
            userResponses.add(userResponse);
        }

        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<UserResponse>> findAllUsers(
            @RequestParam int page,
            @RequestParam int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userService.findAllUsers(pageable);

        Page<UserResponse> userResponses = users.map(UserController::getUserResponse);
        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<UserResponse>> findUsersBySearchTerm(
            @RequestParam String search,
            @RequestParam int page,
            @RequestParam int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userService.findUsersBySearchTerm(search, pageable);

        Page<UserResponse> userResponses = users.map(UserController::getUserResponse);
        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<UserResponse>> getUserById(@PathVariable Integer userId) {
        Optional<User> theUser = userService.getUserById(userId);
        return theUser.map(user -> {
            UserResponse userResponse = getUserResponse(user);
            return ResponseEntity.ok(Optional.of(userResponse));
        }).orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }


    @GetMapping("/recommendation/{userId}")
    public ResponseEntity<Page<UserResponse>> findUsersForRecommendation(
            @PathVariable Integer userId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userService.findUsersForRecommendation(userId, pageable);

        Page<UserResponse> userResponses = users.map(UserController::getUserResponse);
        return ResponseEntity.ok(userResponses);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String usernameOrEmail,
            @RequestParam String password
    ) {

        User user = userService.validateLogin(usernameOrEmail, password);
        if (user != null) {
            return ResponseEntity.ok(getUserResponse(user));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("The username or email or password does not match");
        }
    }

    public static UserResponse getUserResponse(User user) {
        return new UserResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getDepartment(),
                user.getTitle(),
                user.getStartDate(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getManager(),
//                user.getSubordinates(),
                user.getAddress(),
                user.getUserProfile(),
                user.getFollowings(),
                user.getFollowers(),
                user.getTweets(),
                user.getComments()
        );
    }



}
