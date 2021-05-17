package com.picposter.api;

import com.picposter.domain.Post;
import com.picposter.service.api.PostServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
public class PostController {
    private final PostServiceAPI postService;

    @Autowired
    public PostController(@Qualifier("postService") PostServiceAPI postService){
        this.postService = postService;
    }

    @RequestMapping(path = "posts", method = RequestMethod.POST)
    public ResponseEntity<Post> addPost(@RequestBody @NonNull Post post){
        Post postResult = postService.addPost(post);
        return new ResponseEntity<>(postResult, HttpStatus.CREATED);
    }

    @RequestMapping(path = "posts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deletePost(@PathVariable("id") UUID id){
        boolean deleteResult = postService.deletePostById(id);
        if(deleteResult)
            return new ResponseEntity<>(true, HttpStatus.OK);
        else
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "posts/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable("id") UUID userId){
        List<Post> postsResult = postService.getUserPosts(userId);
        if(postsResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(postsResult, HttpStatus.OK);
    }

    @RequestMapping(path = "posts/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> getPostById(@PathVariable("id") UUID id){
        Post postResult = postService.getPostById(id);
        if(postResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(postResult, HttpStatus.OK);
    }

    @RequestMapping(path = "posts", method = RequestMethod.PUT)
    public ResponseEntity<Post> updatePost(Post post){
        Post postResult = postService.updatePost(post);
        if(postResult != null)
            return new ResponseEntity<>(postResult, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
