package com.picposter.service;

import com.picposter.domain.Comment;
import com.picposter.domain.Follow;
import com.picposter.domain.Post;
import com.picposter.domain.User;
import com.picposter.repository.CommentDAO;
import com.picposter.repository.PostDAO;
import com.picposter.repository.UserDAO;
import com.picposter.service.api.CommentServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service("commentService")
public class CommentService implements CommentServiceAPI {
    private final CommentDAO commentDAO;
    private final UserDAO userDAO;
    private final PostDAO postDAO;

    @Autowired
    public CommentService(@Qualifier("commentDAO") CommentDAO commentDAO,
                          @Qualifier("userDAO") UserDAO userDAO,
                          @Qualifier("postDAO") PostDAO postDAO) {
        this.commentDAO = commentDAO;
        this.userDAO = userDAO;
        this.postDAO = postDAO;
    }

    @Override
    public Comment getCommentById(UUID commentId) {
        return commentDAO.findById(commentId).orElse(null);
    }

    @Override
    public List<Comment> getUserComments(UUID userId) {
        User user = userDAO.findById(userId).orElse(null);
        if(user == null)
            return null;
        else
            return user.getComments();
        //return commentDAO.findAll().stream().filter(comment -> comment.getCommentator().getId().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public List<Comment> getPostComments(UUID postId) {
        Post post = postDAO.findById(postId).orElse(null);
        if (post == null)
            return null;
        else
            return post.getComments();
    }

    @Override
    public Comment addComment(Comment comment) {
        Post post = postDAO.findById(comment.getPost().getId()).orElse(null);
        if(post != null && post.isAllowComments()){
            comment.setAddedDate(LocalDateTime.now());
            comment.setId(UUID.randomUUID());
            while(commentDAO.findById(comment.getId()).isPresent())
                comment.setId(UUID.randomUUID());
            return commentDAO.save(comment);
        }
        else
            return null;
    }

    @Override
    public Comment updateComment(Comment updatedComment) {
        Comment resultComment = commentDAO.findById(updatedComment.getId()).orElse(null);
        if(resultComment != null){
            resultComment.setAddedDate(updatedComment.getAddedDate());
            resultComment.setComment(updatedComment.getComment());
            resultComment.setCommentator(updatedComment.getCommentator());
            resultComment.setPost(updatedComment.getPost());
            return commentDAO.save(resultComment);
        }
        return null;
    }

    @Override
    public boolean deleteCommentById(UUID commentId) {
        if(commentDAO.findById(commentId).isPresent()){
            commentDAO.deleteById(commentId);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePostComments(UUID postId) {
        Post post = postDAO.findById(postId).orElse(null);
        if(post != null) {
            for (Comment com : post.getComments()) {
                commentDAO.deleteById(com.getId());
            }
            return true;
        }
        return false;
    }
}
