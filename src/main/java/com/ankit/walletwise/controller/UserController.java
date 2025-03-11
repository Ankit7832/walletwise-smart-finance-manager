package com.ankit.walletwise.controller;

import com.ankit.walletwise.dto.UserRequestDTO;
import com.ankit.walletwise.dto.UserResponseDTO;
import com.ankit.walletwise.entity.User;
import com.ankit.walletwise.repository.UserRepository;
import com.ankit.walletwise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/add")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(userService.addUser(userRequestDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable int id) {
        UserResponseDTO userResponseDTO = userService.getUserById(id);
        return ResponseEntity.ok(userResponseDTO);
    }


}
