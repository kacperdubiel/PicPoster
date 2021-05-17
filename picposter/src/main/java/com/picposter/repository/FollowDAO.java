package com.picposter.repository;

import com.picposter.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowDAO extends JpaRepository<Follow, Integer> {
}
