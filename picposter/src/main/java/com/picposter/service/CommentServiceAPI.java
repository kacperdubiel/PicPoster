package com.picposter.service;

import com.picposter.domain.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentServiceAPI {
    Comment addComment(Comment comment);
    boolean deleteCommentById(UUID id);
    Comment updateComment(Comment updatedComment);
    List<Comment> getUserComments(UUID userId);
    List<Comment> getPostComments(UUID postId);
}
