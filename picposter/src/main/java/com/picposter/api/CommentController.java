package com.picposter.api;

import com.picposter.domain.Comment;
import com.picposter.domain.Follow;
import com.picposter.service.api.CommentServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class CommentController {
    private final CommentServiceAPI commentService;

    @Autowired
    public CommentController(@Qualifier("commentService") CommentServiceAPI commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(path = "comments/{id}", method = RequestMethod.GET)
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") UUID id){
        Comment commentResult = commentService.getCommentById(id);
        if(commentResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(commentResult, HttpStatus.OK);
    }

    @RequestMapping(path = "comments/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getUserComments(@PathVariable("id") UUID userId){
        List<Comment> commentsResult =commentService.getUserComments(userId);
        if(commentsResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
                return new ResponseEntity<>(commentsResult, HttpStatus.OK);
    }

    @RequestMapping(path = "comments/post/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deletePostComments(@PathVariable("id") UUID postId){
        boolean result  =commentService.deletePostComments(postId);
        if(!result)
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        else
            return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @RequestMapping(path = "comments/post/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getPostComments(@PathVariable("id") UUID postId){
        List<Comment> commentsResult =commentService.getPostComments(postId);
        if(commentsResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else {
            commentsResult.sort((Comment c1, Comment c2) -> c1.getAddedDate().compareTo(c2.getAddedDate()));
            return new ResponseEntity<>(commentsResult, HttpStatus.OK);
        }
    }

    @RequestMapping(path = "comments", method = RequestMethod.POST)
    public ResponseEntity<Comment> addComment(@RequestBody @NonNull Comment comment){
        Comment commentResult = commentService.addComment(comment);
        if(commentResult != null)
            return new ResponseEntity<>(commentResult, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @RequestMapping(path = "comments", method = RequestMethod.PUT)
    public ResponseEntity<Comment> updateComment(Comment comment){
        Comment commentResult = commentService.updateComment(comment);
        if(comment != null)
            return new ResponseEntity<>(commentResult, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "comments/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deletePost(@PathVariable("id") UUID id){
        boolean deleteResult = commentService.deleteCommentById(id);
        if(deleteResult)
            return new ResponseEntity<>(true, HttpStatus.OK);
        else
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }


}
