package com.ankit.walletwise.controller;

import com.ankit.walletwise.dto.UserRegistrationDTO;
import com.ankit.walletwise.entity.User;
import com.ankit.walletwise.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findUserByEmail(userDetails.getUsername());

        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("name", user.getName());
        response.put("email", user.getEmail());
        response.put("registrationDate", user.getRegistrationDate());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateCurrentUser(
            @RequestBody UserRegistrationDTO userDTO,
            @AuthenticationPrincipal UserDetails userDetails) {

        User user = userService.findUserByEmail(userDetails.getUsername());

        User updatedUser = new User();
        updatedUser.setName(userDTO.getName());
        updatedUser.setPassword(userDTO.getPassword());

        User savedUser = userService.updateUser(user.getId(), updatedUser);

        Map<String, Object> response = new HashMap<>();
        response.put("id", savedUser.getId());
        response.put("name", savedUser.getName());
        response.put("email", savedUser.getEmail());
        response.put("registrationDate", savedUser.getRegistrationDate());

        return ResponseEntity.ok(response);
    }
}