package com.Intuit.chirped.service;

import com.Intuit.chirped.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> getUserByRandom();

    Page<User> findAllUsers(Pageable pageable);

    Page<User> findUsersBySearchTerm(String search, Pageable pageable);

    Optional<User> getUserById(Integer userId);

    User getUserByEmail(String email);

    User getUserByUsername(String username);

    Page<User> findUsersForRecommendation(Integer userId, Pageable pageable);

    User validateLogin(String usernameOrEmail, String password);

}
