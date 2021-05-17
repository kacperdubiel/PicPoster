package com.picposter.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    private UUID id;
    @Column(name = "comment")
    private String comment;
    @Column(name = "added_date")
    private LocalDateTime addedDate;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User commentator;

    public Comment(@JsonProperty("id") UUID id, @JsonProperty("comment") String comment,
                   @JsonProperty("addedDate") LocalDateTime addedDate,
                   @JsonProperty("post") Post post, @JsonProperty("commentator") User commentator){
        this.id = id;
        this.comment = comment;
        this.addedDate = addedDate;
        this.post = post;
        this.commentator = commentator;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getCommentator() {
        return commentator;
    }

    public void setCommentator(User commentator) {
        this.commentator = commentator;
    }
}
