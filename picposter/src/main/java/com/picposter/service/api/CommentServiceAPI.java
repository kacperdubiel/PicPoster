package com.picposter.service.api;

import com.picposter.domain.Comment;
import com.picposter.domain.Follow;

import java.util.List;
import java.util.UUID;

public interface CommentServiceAPI {
    Comment getCommentById(UUID commentId);
    List<Comment> getUserComments(UUID userId);
    List<Comment> getPostComments(UUID postId);
    Comment addComment(Comment comment);
    Comment updateComment(Comment updatedComment);
    boolean deleteCommentById(UUID id);
}
