package com.picposter.service.api;

import com.picposter.domain.Comment;
import com.picposter.domain.Like;
import java.util.List;
import java.util.UUID;

public interface LikeServiceAPI {
    Like getLikeById(UUID likeId);
    List<Like> getUserLikes(UUID userId);
    List<Like> getPostLikes(UUID postId);
    Like addLike(Like like);
    boolean deleteLikeById(UUID likeId);
}
