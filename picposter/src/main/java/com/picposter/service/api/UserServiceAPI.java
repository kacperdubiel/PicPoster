package com.picposter.service.api;

import com.picposter.domain.User;

import java.util.List;
import java.util.UUID;

public interface UserServiceAPI {
    User getUserById(UUID userId);
    User getUserByLogin(String userLogin);
    User getUserByEmail(String userEmail);
    User addUser(User user);
    List<User> getUsers();
    boolean deleteUserById(UUID userId);
    User updateUser(User updatedUser);
    List<User> searchUser(String startString);
}
