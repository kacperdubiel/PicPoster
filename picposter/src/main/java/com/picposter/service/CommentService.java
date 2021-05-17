package com.picposter.service;

import com.picposter.domain.Comment;
import com.picposter.domain.Post;
import com.picposter.domain.User;
import com.picposter.repository.CommentDAO;
import com.picposter.repository.PostDAO;
import com.picposter.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public Comment addComment(Comment comment) {
        comment.setId(UUID.randomUUID());
        while(commentDAO.findById(comment.getId()).isPresent())
            comment.setId(UUID.randomUUID());
        return commentDAO.save(comment);
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
}
