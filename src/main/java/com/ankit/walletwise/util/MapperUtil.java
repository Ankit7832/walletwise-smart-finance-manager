package com.ankit.walletwise.util;

import com.ankit.walletwise.dto.UserRequestDTO;
import com.ankit.walletwise.dto.UserResponseDTO;
import com.ankit.walletwise.entity.User;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    public User mapToUser(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        return user;
    }
    public UserResponseDTO mapToUserResponse(User user){
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }
}
