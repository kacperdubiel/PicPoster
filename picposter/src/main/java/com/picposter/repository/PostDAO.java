package com.picposter.repository;

import com.picposter.domain.Comment;
import com.picposter.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("postDAO")
public interface PostDAO extends JpaRepository<Post, UUID> {
}
