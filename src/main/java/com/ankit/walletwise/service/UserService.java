package com.ankit.walletwise.service;

import com.ankit.walletwise.dto.UserRequestDTO;
import com.ankit.walletwise.dto.UserResponseDTO;
import com.ankit.walletwise.entity.User;
import com.ankit.walletwise.repository.UserRepository;
import com.ankit.walletwise.util.MapperUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    public UserResponseDTO addUser(UserRequestDTO userRequestDTO){
        if(userRepository.findByEmail(userRequestDTO.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email already exists!");
        }
        User user=mapperUtil.mapToUser(userRequestDTO);

        User savedUser=userRepository.save(user);
        return mapperUtil.mapToUserResponse(savedUser);
    }
    public UserResponseDTO getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return mapperUtil.mapToUserResponse(user);
    }


}
