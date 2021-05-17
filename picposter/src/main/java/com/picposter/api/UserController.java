package com.picposter.api;

import com.picposter.domain.User;
import com.picposter.service.api.UserServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {
    private final UserServiceAPI userService;

    @Autowired
    public UserController(@Qualifier("userService") UserServiceAPI userService){
        this.userService = userService;
    }

    @RequestMapping(path = "users/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id){
        User userResult = userService.getUserById(id);
        if(userResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(userResult, HttpStatus.OK);
    }

    @RequestMapping(path = "users/login/{login}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByLogin(@PathVariable("login") String login){
        User userResult = userService.getUserByLogin(login);
        if(userResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(userResult, HttpStatus.OK);
    }

    @RequestMapping(path = "users/email/{email}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
        User userResult = userService.getUserByEmail(email);
        if(userResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(userResult, HttpStatus.OK);
    }

    @RequestMapping(path = "users", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody @NonNull User user){
        User userResult = userService.addUser(user);
        return new ResponseEntity<>(userResult, HttpStatus.CREATED);
    }

    @RequestMapping(path = "users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteUserById(@PathVariable("id") UUID id){
        boolean deleteResult = userService.deleteUserById(id);
        if(deleteResult)
            return new ResponseEntity<>(true, HttpStatus.OK);
        else
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "users", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(User user){
        User userResult = userService.updateUser(user);
        if(userResult != null)
            return new ResponseEntity<>(userResult, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
