package com.picposter.api;

import com.picposter.domain.Follow;
import com.picposter.domain.Like;
import com.picposter.domain.Post;
import com.picposter.domain.User;
import com.picposter.service.api.FollowServiceAPI;
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
public class FollowController {
    private final FollowServiceAPI followService;
    private final UserServiceAPI userService;

    @Autowired
    public FollowController(@Qualifier("followService") FollowServiceAPI followService,
                            @Qualifier("userService") UserServiceAPI userService){
        this.followService = followService;
        this.userService = userService;
    }

    @RequestMapping(path = "follows/{id}", method = RequestMethod.GET)
    public ResponseEntity<Follow> getFollowById(@PathVariable("id") UUID id){
        Follow followResult = followService.getFollowById(id);
        if(followResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(followResult, HttpStatus.OK);
    }

    @RequestMapping(path = "follows/follower/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Follow>> getFollowsByFollower(@PathVariable("id") UUID id){
        List<Follow> followsResult = followService.getFollowsByFollower(id);
        if(followsResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(followsResult, HttpStatus.OK);
    }

    @RequestMapping(path = "follows/followed/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Follow>> getFollowsByFollowed(@PathVariable("id") UUID id){
        List<Follow> followsResult = followService.getFollowsByFollowed(id);
        if(followsResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(followsResult, HttpStatus.OK);
    }

    @RequestMapping(path = "follows/follower/{followerId}/followed/{followedId}", method = RequestMethod.GET)
    public ResponseEntity<Follow> getFollowByFollowerAndFollowed(@PathVariable("followerId") UUID followerId,
                                                               @PathVariable("followedId") UUID followedId){
        User follower = userService.getUserById(followerId);
        User followed = userService.getUserById(followedId);
        if(follower == null || followed == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // User or Post not found

        Follow followResult = followService.getFollowByFollowerAndFollowed(follower, followed);
        if(followResult == null)
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT); // User and Post found, but no such Like
        else
            return new ResponseEntity<>(followResult, HttpStatus.OK);
    }

    @RequestMapping(path = "follows", method = RequestMethod.POST)
    public ResponseEntity<Follow> addFollow(@RequestBody @NonNull Follow follow){
        User follower = follow.getFollower();
        User followed = follow.getFollowed();

        if(follower == null || followed == null || follower.getId().equals(followed.getId()))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        Follow duplicate = followService.getFollowByFollowerAndFollowed(follower, followed);
        if(duplicate != null)
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);

        follow.setFollowDate(LocalDateTime.now());
        Follow followResult = followService.addFollow(follow);
        return new ResponseEntity<>(followResult, HttpStatus.CREATED);
    }

    @RequestMapping(path = "follows/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteFollowById(@PathVariable("id") UUID id){
        boolean deleteResult = followService.deleteFollowById(id);
        if(deleteResult)
            return new ResponseEntity<>(true, HttpStatus.OK);
        else
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
