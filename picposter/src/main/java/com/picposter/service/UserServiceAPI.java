package com.picposter.service;

import com.picposter.domain.User;

import java.util.UUID;

public interface UserServiceAPI {
    User getUserById(UUID userId);
    User getUserByLogin(String userLogin);
    User getUserByEmail(String userEmail);
    User addUser(User user);
    boolean deleteUserById(UUID userId);
    User updateUser(User updatedUser);
}
