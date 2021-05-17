package com.picposter.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "follows")
public class Follow {
    @Id
    private UUID id;
    @ManyToOne
    private User follower;
    @ManyToOne
    private User followed;
    @Column(name = "follow_date")
    private LocalDateTime followDate;

    public Follow(@JsonProperty("id") UUID id, @JsonProperty("follower") User follower, @JsonProperty("followed") User followed,
                  @JsonProperty("followDate") LocalDateTime followDate){
        this.id = id;
        this.follower = follower;
        this.followed = followed;
        this.followDate = followDate;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowed() {
        return followed;
    }

    public void setFollowed(User followed) {
        this.followed = followed;
    }

    public LocalDateTime getFollowDate() {
        return followDate;
    }

    public void setFollowDate(LocalDateTime followDate) {
        this.followDate = followDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
