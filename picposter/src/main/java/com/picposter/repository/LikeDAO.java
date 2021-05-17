package com.picposter.repository;

import com.picposter.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LikeDAO extends JpaRepository<Comment, UUID> {
}
