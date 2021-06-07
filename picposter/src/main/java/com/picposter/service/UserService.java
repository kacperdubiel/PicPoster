package com.picposter.service;

import com.picposter.domain.User;
import com.picposter.repository.UserDAO;
import com.picposter.service.api.UserServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.plaf.OptionPaneUI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("userService")
public class UserService implements UserServiceAPI, UserDetailsService {
    private UserDAO userDAO;

    @Autowired
    public UserService(@Qualifier("userDAO") UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public User getUserById(UUID userId) {
        return userDAO.findById(userId).orElse(null);
    }

    @Override
    public User getUserByLogin(String userLogin) {
        List<User> allUsers = userDAO.findAll();
        Optional<User> foundUser = allUsers.stream().filter(user -> user.getLogin().equals(userLogin)).findFirst();
        return foundUser.orElse(null);
    }

    @Override
    public List<User> getUsers() {
        return userDAO.findAll();
    }

    @Override
    public List<User> searchUser(String startString) {
        List<User> users = userDAO.findAll();
        return users.stream()
                .filter(user -> user.getLogin().toLowerCase().startsWith(startString.toLowerCase()))
                .limit(10)
                .collect(Collectors.toList());
    }

    @Override
    public User getUserByEmail(String userEmail) {
        List<User> allUsers = userDAO.findAll();
        Optional<User> foundUser = allUsers.stream().filter(user -> user.getEmail().equals(userEmail)).findFirst();
        return foundUser.orElse(null);
    }

    @Override
    public User addUser(User user) {
        user.setId(UUID.randomUUID());
       if(getUserByEmail(user.getEmail()) != null || getUserByLogin(user.getLogin()) != null)
           return null;
        while(userDAO.findById(user.getId()).isPresent())
            user.setId(UUID.randomUUID());
        try {
            return userDAO.save(user);
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean deleteUserById(UUID userId) {
        if(userDAO.findById(userId).isPresent()){
            userDAO.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public User updateUser(User updatedUser) {
        User resultUser = userDAO.findById(updatedUser.getId()).orElse(null);
        if(resultUser != null){
            resultUser.setLogin(updatedUser.getLogin());
            resultUser.setPassword(updatedUser.getPassword());
            resultUser.setEmail(updatedUser.getEmail());
            resultUser.setLastLoginDate(updatedUser.getLastLoginDate());
            resultUser.setCreatedDate(updatedUser.getCreatedDate());
            resultUser.setDescription(updatedUser.getDescription());
            resultUser.setProfileImagePath(updatedUser.getProfileImagePath());
            return userDAO.save(resultUser);
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User u =  getUserByLogin(s);
        if(u == null)
            throw new UsernameNotFoundException("username "+s+" not found");
        return u;
    }
}
