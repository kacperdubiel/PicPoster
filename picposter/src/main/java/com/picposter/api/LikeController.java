package com.picposter.api;

import com.picposter.domain.Like;
import com.picposter.service.LikeServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class LikeController {
    private final LikeServiceAPI likeService;

    @Autowired
    public LikeController(@Qualifier("likeService") LikeServiceAPI likeService) {
        this.likeService = likeService;
    }

    @RequestMapping(path = "likes", method = RequestMethod.POST)
    public ResponseEntity<Like> addLike(@RequestBody @NonNull Like like){
        Like likeResult = likeService.addLike(like);
        return new ResponseEntity<>(likeResult, HttpStatus.CREATED);
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
        List<Like> likesResult =likeService.getPostLikes(postId);
        if(likesResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(likesResult, HttpStatus.OK);
    }

    @RequestMapping(path = "comments/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteLike(@PathVariable("id") UUID id){
        boolean deleteResult = likeService.deleteLikeById(id);
        if(deleteResult)
            return new ResponseEntity<>(true, HttpStatus.OK);
        else
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
