package com.picposter.repository;

import com.picposter.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeDAO extends JpaRepository<Comment, Integer> {
}
