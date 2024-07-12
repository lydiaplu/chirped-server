package com.Intuit.chirped.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    private Integer userId;

    private String displayName;
    private String password;
    private String bio;
    private String profilePicUrl;

    @Column(nullable = false)
    private Date createdAt;

    @Column
    private Date updatedAt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public UserProfile(){

    }

    public void setCreatedAt() {
        this.createdAt = new Date();
    }

    public void setUpdatedAt() {
        this.updatedAt = new Date();
    }
}
