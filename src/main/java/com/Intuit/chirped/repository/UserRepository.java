package com.Intuit.chirped.repository;

import com.Intuit.chirped.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM users ORDER BY RAND() LIMIT 100", nativeQuery = true)
    List<User> getUserByRandom();

    @Query("SELECT u FROM User u " +
            "LEFT JOIN u.userProfile up " +
            "LEFT JOIN u.address a " +
            "WHERE (u.username LIKE %:search% OR up.displayName LIKE %:search%) " +
            "OR u.department LIKE %:search% OR u.title LIKE %:search% " +
            "OR a.city LIKE %:search% OR a.state LIKE %:search% " +
            "OR a.country LIKE %:search% OR a.postalCode LIKE %:search%")
    Page<User> findUsersBySearchTerm(@Param("search") String search, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE " +
            "u.userId NOT IN (SELECT f.following.userId FROM Follow f WHERE f.follower.userId = :userId) " +
            "AND u.userId != :userId " +
            "AND (" +
            "u.manager = (SELECT um.manager FROM User um WHERE um.userId = :userId) OR " +
            "u.title = (SELECT ut.title FROM User ut WHERE ut.userId = :userId) OR " +
            "u.address = (SELECT ua.address FROM User ua WHERE ua.userId = :userId) OR " +
            "1=1) " +
            "ORDER BY FUNCTION('RAND')")
    Page<User> findUsersForRecommendation(@Param("userId") Integer userId, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.username = :usernameOrEmail OR u.email = :usernameOrEmail")
    User findByUsernameOrEmail(String usernameOrEmail);

//    @Query("SELECT u FROM User u WHERE " +
//            "(u.manager = (SELECT um.manager FROM User um WHERE um.userId = :userId) OR " +
//            "u.title = (SELECT ut.title FROM User ut WHERE ut.userId = :userId) OR " +
//            "u.address = (SELECT ua.address FROM User ua WHERE ua.userId = :userId)) " +
//            "AND u.userId NOT IN (SELECT f.following.userId FROM Follow f WHERE f.follower.userId = :userId) " +
//            "AND u.userId NOT IN (SELECT f.following.userId FROM Follow f WHERE f.follower = (SELECT um.manager FROM User um WHERE um.userId = :userId)) " +
//            "AND u.userId != :userId " +
//            "ORDER BY FUNCTION('RAND')")
//    Page<User> findUsersForRecommendation(@Param("userId") Integer userId, Pageable pageable);

//    @Query(value = "SELECT * FROM users WHERE (username LIKE %:search% OR title LIKE %:search% OR department LIKE %:search% OR location LIKE %:search%) ORDER BY RAND() LIMIT 100", nativeQuery = true)
//    List<User> findUsersBySearchTermRandomly(@Param("search") String search);


}
