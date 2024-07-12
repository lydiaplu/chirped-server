package com.Intuit.chirped.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "follows")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer followId;

    @Column(nullable = false)
    private Date createdAt;

    // who follows the user
    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "userId")
    private User follower;

    // the user id
    @ManyToOne
    @JoinColumn(name = "following_id", referencedColumnName = "userId")
    private User following;

    public Follow() {

    }
}
