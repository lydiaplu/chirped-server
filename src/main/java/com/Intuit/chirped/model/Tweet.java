package com.Intuit.chirped.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "tweets")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tweetId;

    @Column(nullable = false, length = 5000)
    private String content;

    @Column(nullable = false)
    private Date createdAt;

    @Column
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @OneToMany(mappedBy = "tweet",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<TweetImage> images;

    @OneToMany(mappedBy = "tweet",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Tweet() {
        this.images = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public void addImage(TweetImage image) {
        this.images.add(image);
        image.setTweet(this);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setTweet(this);
    }
}
