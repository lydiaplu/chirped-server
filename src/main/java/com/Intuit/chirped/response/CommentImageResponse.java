package com.Intuit.chirped.response;

import com.Intuit.chirped.model.Comment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentImageResponse {
    private Integer imageId;
    private String imageUrl;
    private Comment comment;
}
