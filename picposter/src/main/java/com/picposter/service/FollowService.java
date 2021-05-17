package com.picposter.service;

import com.picposter.domain.Follow;
import com.picposter.repository.FollowDAO;
import com.picposter.repository.UserDAO;
import com.picposter.service.api.FollowServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("followService")
public class FollowService implements FollowServiceAPI {
    private FollowDAO followDAO;
    private UserDAO userDAO;

    @Autowired
    public FollowService(@Qualifier("followDAO") FollowDAO followDAO, @Qualifier("userDAO") UserDAO userDAO){
        this.followDAO = followDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Follow getFollowById(UUID followId) {
        return followDAO.findById(followId).orElse(null);
    }

    @Override
    public List<Follow> getFollowsByFollower(UUID followerId) {
        if(!userDAO.findById(followerId).isPresent())
            return null;
        List<Follow> allFollows = followDAO.findAll();
        return allFollows.stream().filter(f -> f.getFollower().getId().equals(followerId)).collect(Collectors.toList());
    }

    @Override
    public List<Follow> getFollowsByFollowed(UUID followedId) {
        if(!userDAO.findById(followedId).isPresent())
            return null;
        List<Follow> allFollows = followDAO.findAll();
        return allFollows.stream().filter(f -> f.getFollowed().getId().equals(followedId)).collect(Collectors.toList());
    }

    @Override
    public Follow addFollow(Follow follow) {
        follow.setId(UUID.randomUUID());
        while(followDAO.findById(follow.getId()).isPresent())
            follow.setId(UUID.randomUUID());
        return followDAO.save(follow);
    }

    @Override
    public boolean deleteFollowById(UUID followId) {
        if(followDAO.findById(followId).isPresent()){
            followDAO.deleteById(followId);
            return true;
        }
        return false;
    }
}
