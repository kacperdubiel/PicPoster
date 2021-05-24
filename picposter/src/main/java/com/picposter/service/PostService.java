package com.picposter.service;

import com.picposter.domain.Follow;
import com.picposter.domain.Post;
import com.picposter.domain.User;
import com.picposter.repository.FollowDAO;
import com.picposter.repository.PostDAO;
import com.picposter.repository.UserDAO;
import com.picposter.service.api.PostServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("postService")
public class PostService implements PostServiceAPI {
    private PostDAO postDAO;
    private UserDAO userDAO;
    private FollowDAO followDAO;

    @Autowired
    public PostService(@Qualifier("postDAO") PostDAO postDAO, @Qualifier("userDAO") UserDAO userDAO,
                       @Qualifier("followDAO") FollowDAO followDAO){
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.followDAO = followDAO;
    }

    @Override
    public Post getPostById(UUID postId) {
        return postDAO.findById(postId).orElse(null);
    }

    @Override
    public List<Post> getUserPosts(UUID userId) {
        User user = userDAO.findById(userId).orElse(null);
        if(user == null)
            return null;
        else
            return user.getPosts();
    }

    @Override
    public List<Post> getFollowedPosts(UUID userId) {
        User user = userDAO.findById(userId).orElse(null);
        if(user == null)
            return null;

        List<Post> followedPosts = new ArrayList<>();
        List<Follow> followedUsers = user.getFollowings();

        if(followedUsers != null){
            for(Follow f : followedUsers) {
                followedPosts = Stream.concat(followedPosts.stream(), f.getFollowed().getPosts().stream())
                        .collect(Collectors.toList());
            }
        }
        followedPosts = followedPosts.stream()
                .sorted(Comparator.comparing(Post :: getAddedDate).reversed())
                .collect(Collectors.toList());

        return followedPosts;
    }

    @Override
    public Post addPost(Post post) {
        post.setId(UUID.randomUUID());
        while(postDAO.findById(post.getId()).isPresent())
            post.setId(UUID.randomUUID());
        return postDAO.save(post);
    }

    @Override
    public Post updatePost(Post updatedPost) {
        Post resultPost = postDAO.findById(updatedPost.getId()).orElse(null);
        if(resultPost != null){
            resultPost.setAddedDate(updatedPost.getAddedDate());
            resultPost.setAllowComments(updatedPost.isAllowComments());
            resultPost.setComments(updatedPost.getComments());
            resultPost.setDescription(updatedPost.getDescription());
            resultPost.setImagePath(updatedPost.getImagePath());
            resultPost.setLikes(updatedPost.getLikes());
            resultPost.setPoster(updatedPost.getPoster());
            resultPost.setOrder(updatedPost.getOrder());
            return postDAO.save(resultPost);
        }
        return null;
    }

    @Override
    public boolean deletePostById(UUID postId) {
        if(postDAO.findById(postId).isPresent()){
            postDAO.deleteById(postId);
            return true;
        }
        return false;
    }
}
