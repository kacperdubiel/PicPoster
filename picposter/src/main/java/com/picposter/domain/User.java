package com.picposter.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    private UUID id;
    @Column(name = "login", unique=true)
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "email", unique=true)
    private String email;
    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "description")
    private String description;
    @Column(name = "profile_image_path")
    private String profileImagePath;
    @JsonIgnore
    @OneToMany (mappedBy = "poster")
    private List<Post> posts;
    @JsonIgnore
    @OneToMany (mappedBy = "followed")
    private List<Follow> followers;
    @JsonIgnore
    @OneToMany (mappedBy = "follower")
    private List<Follow> followings;
    @JsonIgnore
    @OneToMany (mappedBy = "commentator")
    private List<Comment> comments;
    @JsonIgnore
    @OneToMany (mappedBy = "liker")
    private List<Like> likes;

    public User() {
    }

    public User(@JsonProperty("id") UUID id,
                @JsonProperty("login") String login,
                @JsonProperty("password") String password, @JsonProperty("email") String email,
                @JsonProperty("lastLoginDate") LocalDateTime lastLoginDate,
                @JsonProperty("createdDate") LocalDateTime createdDate,
               @JsonProperty("description") String description, @JsonProperty("profileImagePath") String profileImagePath){
        this.login = login;
        this.password = password;
        this.email = email;
        this.lastLoginDate = lastLoginDate;
        this.createdDate = createdDate;
        this.description = description;
        this.profileImagePath = profileImagePath;
        this.id = id;

        posts = new ArrayList<>();
        followers = new ArrayList<>();
        followings = new ArrayList<>();
        comments = new ArrayList<>();
        likes = new ArrayList<>();
    }


    public List<Follow> getFollowings() {
        return followings;
    }

    public void setFollowings(ArrayList<Follow> followings) {
        this.followings = followings;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String getDescription() {
        return description;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Follow> getFollowers() {
        return followers;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public void setFollowers(ArrayList<Follow> followers) {
        this.followers = followers;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }
}
