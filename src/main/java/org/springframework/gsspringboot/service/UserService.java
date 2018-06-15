package org.springframework.gsspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.gsspringboot.model.User;
import org.springframework.gsspringboot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public void saveNewUser(User user){
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
