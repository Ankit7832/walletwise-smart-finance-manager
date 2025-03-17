package com.ankit.walletwise.service;

import com.ankit.walletwise.entity.User;
import com.ankit.walletwise.exception.ResourceNotFoundException;
import com.ankit.walletwise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new IllegalArgumentException("Email already Exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findById(id).orElseThrow(()-> new
                ResourceNotFoundException("User Not Found with Id:" +id));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new
                ResourceNotFoundException("User Not Found with Email:" +email));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User updateUser(int id, User userDetails) {
        User user= findUserById(id);

        user.setName(userDetails.getName());

        if (userDetails.getPassword()!=null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }

        return userRepository.save(user);
    }
}
