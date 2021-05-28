package com.picposter.api;

import com.picposter.domain.Comment;
import com.picposter.domain.Like;
import com.picposter.domain.Post;
import com.picposter.domain.User;
import com.picposter.service.api.LikeServiceAPI;
import com.picposter.service.api.PostServiceAPI;
import com.picposter.service.api.UserServiceAPI;
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
public class LikeController {
    private final LikeServiceAPI likeService;
    private final UserServiceAPI userService;
    private final PostServiceAPI postService;

    @Autowired
    public LikeController(@Qualifier("likeService") LikeServiceAPI likeService,
                          @Qualifier("userService") UserServiceAPI userService,
                          @Qualifier("postService") PostServiceAPI postService) {
        this.likeService = likeService;
        this.userService = userService;
        this.postService = postService;
    }

    @RequestMapping(path = "likes/{id}", method = RequestMethod.GET)
    public ResponseEntity<Like> getLikeById(@PathVariable("id") UUID id){
        Like likeResult = likeService.getLikeById(id);
        if(likeResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(likeResult, HttpStatus.OK);
    }

    @RequestMapping(path = "likes/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Like>> getUserLikes(@PathVariable("id") UUID userId){
        List<Like> likeResult =likeService.getUserLikes(userId);
        if(likeResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(likeResult, HttpStatus.OK);
    }

    @RequestMapping(path = "likes/post/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Like>> getPostLikes(@PathVariable("id") UUID postId){
        List<Like> likesResult = likeService.getPostLikes(postId);
        if(likesResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(likesResult, HttpStatus.OK);
    }

    @RequestMapping(path = "likes/user/{userId}/post/{postId}", method = RequestMethod.GET)
    public ResponseEntity<Like> getLikeByLikerAndPost(@PathVariable("userId") UUID userId,
                                                      @PathVariable("postId") UUID postId){
        User liker = userService.getUserById(userId);
        Post post = postService.getPostById(postId);

        Like likeResult = likeService.getLikeByLikerAndPost(liker, post);
        if(likeResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(likeResult, HttpStatus.OK);
    }

    @RequestMapping(path = "likes", method = RequestMethod.POST)
    public ResponseEntity<Like> addLike(@RequestBody @NonNull Like like){
        Like duplicate = likeService.getLikeByLikerAndPost(like.getLiker(), like.getPost());
        if(duplicate != null)
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);

        like.setAddedDate(LocalDateTime.now());
        Like likeResult = likeService.addLike(like);
        return new ResponseEntity<>(likeResult, HttpStatus.CREATED);
    }

    @RequestMapping(path = "likes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteLike(@PathVariable("id") UUID id){
        boolean deleteResult = likeService.deleteLikeById(id);
        if(deleteResult)
            return new ResponseEntity<>(true, HttpStatus.OK);
        else
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
