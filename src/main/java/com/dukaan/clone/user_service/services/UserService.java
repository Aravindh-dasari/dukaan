package com.dukaan.clone.user_service.services;

import com.dukaan.clone.user_service.dto.LoginRequest;
import com.dukaan.clone.user_service.exception.UserAlreadyExistsException;
import com.dukaan.clone.user_service.models.User;
import com.dukaan.clone.user_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) throws UserAlreadyExistsException {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username is already taken");
        }

        if(userRepository.findByUsername(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email is already registered");
        }

//        Hash the password and store in DataBase
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
