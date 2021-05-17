package com.picposter.repository;
import com.picposter.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("commentDAO")
public interface CommentDAO  extends JpaRepository<Comment, UUID> {
}
