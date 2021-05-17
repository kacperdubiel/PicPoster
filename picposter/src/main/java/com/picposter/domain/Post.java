package com.picposter.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    private UUID id;
    //@Column(name = "image_path")
    private String imagePath;
    //@Column(name = "description")
    private String description;
    //@Column(name = "allow_comments")
    private boolean allowComments;
    //@Column(name = "added_date")
    private LocalDateTime addedDate;
    //@Column(name = "order")
    private int order;
    @ManyToOne
    private User poster;
    @OneToMany (mappedBy = "post")
    private List<Like> likes;
    @OneToMany (mappedBy = "post")
    private List<Comment> comments;


    public Post(@JsonProperty("id") UUID id, @JsonProperty ("imagePath") String imagePath,
                @JsonProperty("description") String description, @JsonProperty("allowComments") boolean allowComments,
                @JsonProperty("addedDate") LocalDateTime addedDate, @JsonProperty("order") int order,
                @JsonProperty("poster") User poster){
        this.id = id;
        this.imagePath = imagePath;
        this.description = description;
        this.allowComments = allowComments;
        this.addedDate = addedDate;
        this.order = order;
        this.poster = poster;
        this.likes = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAllowComments() {
        return allowComments;
    }

    public void setAllowComments(boolean allowComments) {
        this.allowComments = allowComments;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Like> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
