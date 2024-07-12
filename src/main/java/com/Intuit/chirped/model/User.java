package com.Intuit.chirped.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private Date createdAt;

    @Column
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "userId")
    private User manager;

//    @OneToOne(mappedBy = "manager",
//            fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private List<User> subordinates;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;

    @OneToOne(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
//    @JsonIgnore
    private UserProfile userProfile;

    // the user follows the people list
    @OneToMany(mappedBy = "follower",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Follow> followings;

    // who follows the user
    @OneToMany(mappedBy = "following",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Follow> followers;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Tweet> tweets;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments;

    public User() {
        this.followings = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.tweets = new ArrayList<>();
        this.comments = new ArrayList<>();
//        this.subordinates = new ArrayList<>();
    }

    public void addFollowing(Follow following) {
        this.followings.add(following);
        following.setFollower(this);
    }

    public void addFollower(Follow follower) {
        this.followers.add(follower);
        follower.setFollowing(this);
    }

    public void addTweet(Tweet tweet) {
        this.tweets.add(tweet);
        tweet.setUser(this);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setUser(this);
    }

//    public void addSubordinate(User subordinate) {
//        this.subordinates.add(subordinate);
//        subordinate.setManager(this);
//    }
}
