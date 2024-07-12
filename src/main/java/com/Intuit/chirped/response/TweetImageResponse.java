package com.Intuit.chirped.response;

import com.Intuit.chirped.model.Tweet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetImageResponse {
    private Integer imageId;
    private String imageUrl;
    private Tweet tweet;
}
