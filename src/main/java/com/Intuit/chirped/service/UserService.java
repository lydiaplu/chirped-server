package com.Intuit.chirped.service;

import com.Intuit.chirped.model.User;
import com.Intuit.chirped.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getUserByRandom() {
        return userRepository.getUserByRandom();
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findUsersBySearchTerm(String search, Pageable pageable) {
        return userRepository.findUsersBySearchTerm(search, pageable);
    }

    @Override
    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Page<User> findUsersForRecommendation(Integer userId, Pageable pageable) {
        return userRepository.findUsersForRecommendation(userId, pageable);
    }

    @Override
    public User validateLogin(String usernameOrEmail, String password) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail);
        if (user != null && user.getUserProfile() != null) {
            String storedPassword = user.getUserProfile().getPassword();
            if (storedPassword.equals(password)) {
                return user;  // 返回User对象
            }
        }
        return null;
    }
}
