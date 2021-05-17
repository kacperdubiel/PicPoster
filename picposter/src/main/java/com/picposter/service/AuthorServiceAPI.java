package com.picposter.service;

import com.picposter.domain.Post;

import java.util.List;
import java.util.UUID;

public interface AuthorServiceAPI {
    List<Post> getUserPost(UUID userId);
    Post addUserPost(UUID userId, Post post);

}
