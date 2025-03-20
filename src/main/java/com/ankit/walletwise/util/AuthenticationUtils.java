package com.ankit.walletwise.util;

import com.ankit.walletwise.entity.User;
import com.ankit.walletwise.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtils {

    private final UserService userService;

    public AuthenticationUtils(UserService userService) {
        this.userService = userService;
    }

    public int getUserIdFromEmail(String email) {
        User user = userService.findUserByEmail(email);
        return user.getId();
    }
}