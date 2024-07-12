package com.Intuit.chirped.repository;

import com.Intuit.chirped.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {


}
