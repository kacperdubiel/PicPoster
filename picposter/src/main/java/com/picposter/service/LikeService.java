package com.picposter.service;

import com.picposter.domain.*;
import com.picposter.repository.LikeDAO;
import com.picposter.repository.PostDAO;
import com.picposter.repository.UserDAO;
import com.picposter.service.api.LikeServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("likeService")
public class LikeService implements LikeServiceAPI {
    private final UserDAO userDAO;
    private final PostDAO postDAO;
    private final LikeDAO likeDAO;

    @Autowired
    public LikeService(@Qualifier("userDAO") UserDAO userDAO, @Qualifier("postDAO")PostDAO postDAO,
                       @Qualifier("likeDAO") LikeDAO likeDAO) {
        this.userDAO = userDAO;
        this.postDAO = postDAO;
        this.likeDAO = likeDAO;
    }

    @Override
    public Like getLikeById(UUID likeId) {
        return likeDAO.findById(likeId).orElse(null);
    }

    @Override
    public List<Like> getUserLikes(UUID userId) {
        User user = userDAO.findById(userId).orElse(null);
        if(user == null)
            return null;
        else
            return user.getLikes();
    }

    @Override
    public List<Like> getPostLikes(UUID postId) {
        Post post = postDAO.findById(postId).orElse(null);
        if(post == null)
            return  null;
        else
            return post.getLikes();
    }

    @Override
    public Like getLikeByLikerAndPost(User liker, Post post) {
        return likeDAO.findByLikerAndPost(liker, post);
    }

    @Override
    public Like addLike(Like like) {
        like.setId(UUID.randomUUID());
        while(likeDAO.findById(like.getId()).isPresent())
            like.setId(UUID.randomUUID());
        return likeDAO.save(like);
    }

    @Override
    public boolean deleteLikeById(UUID likeId) {
        if(likeDAO.findById(likeId).isPresent()){
            likeDAO.deleteById(likeId);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean deletePostLikes(UUID postId) {
        Post post = postDAO.findById(postId).orElse(null);
        if(post != null) {
            for (Like like : post.getLikes()) {
                likeDAO.deleteById(like.getId());
            }
            return true;
        }
        return false;
    }
}
