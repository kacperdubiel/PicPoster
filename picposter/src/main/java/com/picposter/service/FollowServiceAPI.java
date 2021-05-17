package com.picposter.service;

import com.picposter.domain.Follow;
import com.picposter.domain.User;

import java.util.List;
import java.util.UUID;

public interface FollowServiceAPI {
    Follow getFollowById(UUID followId);
    List<Follow> getFollowsByFollower(UUID followerId);
    List<Follow> getFollowsByFollowed(UUID followedId);
    Follow addFollow(Follow follow);
    boolean deleteFollowById(UUID followId);
}
