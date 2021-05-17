package com.picposter.repository;

import com.picposter.domain.Comment;
import com.picposter.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("likeDAO")
public interface LikeDAO extends JpaRepository<Like, UUID> {
}
