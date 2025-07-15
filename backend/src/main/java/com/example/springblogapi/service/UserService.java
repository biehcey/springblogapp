package com.example.springblogapi.service;

import com.example.springblogapi.dto.UserRequestDTO;
import com.example.springblogapi.dto.UserResponseDTO;
import com.example.springblogapi.entity.User;
import com.example.springblogapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DTOMapperService dtoMapperService;

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
        if(userRepository.existsByEmail(userRequestDTO.getEmail())){
            throw new RuntimeException("Email is already in use");
        }
        User user = dtoMapperService.mapToUserEntity(userRequestDTO);
        User saved = userRepository.save(user);
        return dtoMapperService.mapToUserResponseDTO(saved);
    }
    public UserResponseDTO getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return dtoMapperService.mapToUserResponseDTO(user);
    }
    public User getUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
