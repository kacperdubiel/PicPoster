package com.picposter.service.api;

import com.picposter.domain.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostServiceAPI {
    List<Post> getUserPosts(UUID userId);
    Post addPost(Post post);
    boolean deletePostById(UUID postId);
    Post getPostById(UUID postId);
    Post updatePost(Post updatedPost);
}
