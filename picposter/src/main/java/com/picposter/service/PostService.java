package com.picposter.service;

import com.picposter.domain.Post;
import com.picposter.domain.User;
import com.picposter.repository.PostDAO;
import com.picposter.repository.UserDAO;
import com.picposter.service.api.PostServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service("postService")
public class PostService implements PostServiceAPI {
    private PostDAO postDAO;
    private UserDAO userDAO;

    @Autowired
    public PostService(@Qualifier("postDAO") PostDAO postDAO, @Qualifier("userDAO") UserDAO userDAO){
        this.postDAO = postDAO;
        this.userDAO = userDAO;
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
    public Post addPost(Post post) {
        post.setId(UUID.randomUUID());
        while(postDAO.findById(post.getId()).isPresent())
            post.setId(UUID.randomUUID());
        return postDAO.save(post);
    }

    @Override
    public boolean deletePostById(UUID postId) {
        if(postDAO.findById(postId).isPresent()){
            postDAO.deleteById(postId);
            return true;
        }
        return false;
    }

    @Override
    public Post getPostById(UUID postId) {
        return postDAO.findById(postId).orElse(null);
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
}
