package com.ankit.walletwise.controller;

import com.ankit.walletwise.dto.UserRequestDTO;
import com.ankit.walletwise.dto.UserResponseDTO;
import com.ankit.walletwise.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;
    @PostMapping("/add")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(userServiceImpl.addUser(userRequestDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable int id) {
        UserResponseDTO userResponseDTO = userServiceImpl.getUserById(id);
        return ResponseEntity.ok(userResponseDTO);
    }


}
