package com.Intuit.chirped.repository;

import com.Intuit.chirped.model.CommentImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentImageRepository extends JpaRepository<CommentImage, Long> {
}
