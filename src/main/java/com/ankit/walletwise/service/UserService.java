package com.ankit.walletwise.service;

import com.ankit.walletwise.entity.User;

public interface UserService {
    User registerUser(User user);
    User findUserById(int id);
    User findUserByEmail(String email);
    boolean existsByEmail(String email);
    User updateUser(int id,User userDetails);
}
