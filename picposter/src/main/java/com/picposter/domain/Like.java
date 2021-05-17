package com.picposter.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "likes")
public class Like {
    @Id
    private UUID id;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User liker;
    @Column(name = "added_date")
    private LocalDateTime addedDate;


    public Like(@JsonProperty("id") UUID id, @JsonProperty("post") Post post, @JsonProperty("liker") User liker,
                @JsonProperty("addedDate") LocalDateTime addedDate){
        this.id = id;
        this.post = post;
        this.liker = liker;
        this.addedDate = addedDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getLiker() {
        return liker;
    }

    public void setLiker(User liker) {
        this.liker = liker;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }
}
