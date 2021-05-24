package com.picposter.service.api;

import com.picposter.domain.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostServiceAPI {
    Post getPostById(UUID postId);
    List<Post> getUserPosts(UUID userId);
    List<Post> getFollowedPosts(UUID userId);
    Post addPost(Post post);
    Post updatePost(Post updatedPost);
    boolean deletePostById(UUID postId);
}
