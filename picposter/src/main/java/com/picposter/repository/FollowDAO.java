package com.picposter.repository;

import com.picposter.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FollowDAO extends JpaRepository<Follow, UUID> {
}
