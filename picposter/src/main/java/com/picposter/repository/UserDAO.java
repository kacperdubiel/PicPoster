package com.picposter.repository;

import com.picposter.domain.Comment;
import com.picposter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("userDAO")
public interface UserDAO extends JpaRepository<User, UUID> {
}
