package com.picposter.api;

import com.picposter.domain.Follow;
import com.picposter.service.api.FollowServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
public class FollowController {
    private final FollowServiceAPI followService;

    @Autowired
    public FollowController(@Qualifier("followService") FollowServiceAPI followService){
        this.followService = followService;
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

    @RequestMapping(path = "follows", method = RequestMethod.POST)
    public ResponseEntity<Follow> addFollow(@RequestBody @NonNull Follow follow){
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
