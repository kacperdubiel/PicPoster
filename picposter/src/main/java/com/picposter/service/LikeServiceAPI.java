package com.picposter.service;

import com.picposter.domain.Like;
import java.util.List;
import java.util.UUID;

public interface LikeServiceAPI {
    List<Like> getUserLikes(UUID userId);
    List<Like> getPostLikes(UUID postId);
    boolean deleteLikeById(UUID likeId);
    Like addLike(Like like);
}
