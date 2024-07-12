package com.Intuit.chirped.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "tweet_id", nullable = false)
    @JsonIgnore
    private Tweet tweet;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // reply common part
    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    @JsonIgnore
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> replies;

    @OneToMany(mappedBy = "comment",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<CommentImage> images;

    public Comment() {
        this.images = new ArrayList<>();
        this.replies = new ArrayList<>();
    }

    public void addImage(CommentImage image) {
        this.images.add(image);
        image.setComment(this);
    }

    public void addReply(Comment comment) {
        this.replies.add(comment);
        comment.setParentComment(this);
    }
}
