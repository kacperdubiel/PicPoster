package com.picposter.repository;

import com.picposter.domain.Follow;
import com.picposter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("followDAO")
public interface FollowDAO extends JpaRepository<Follow, UUID> {
    Follow findByFollowerAndFollowed(User follower, User followed);
}
