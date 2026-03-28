package org.example.gymmanagement_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.gymmanagement_api.dto.request.UserRequestDto;
import org.example.gymmanagement_api.dto.response.UserResponseDto;
import org.example.gymmanagement_api.entity.User;
import org.example.gymmanagement_api.exception.BusinessRuleException;
import org.example.gymmanagement_api.exception.ResourceNotFoundException;
import org.example.gymmanagement_api.repository.UserRepository;
import org.example.gymmanagement_api.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createUser(UserRequestDto request){
        if (userRepository.existsByEmail(request.getEmail())){
            throw new BusinessRuleException("Hata : Böyle bir email zaten kayıtlı");
        }
        User createUser = modelMapper.map(request,User.class);
        createUser.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(createUser);
        return modelMapper.map(savedUser,UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> user = userRepository.findAll();
        return user.stream()
                .map(User -> modelMapper.map(User,UserResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User getUserbyId = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hata : User bulunamadı"));
        return modelMapper.map(getUserbyId,UserResponseDto.class);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hata : Güncellemek istediğiniz User bulunamadı"));
        modelMapper.map(userRequestDto,existingUser);
        User savedUser = userRepository.save(existingUser);
        return modelMapper.map(savedUser,UserResponseDto.class);
    }

    @Override
    public void deleteUserById(Long id){
        if (!userRepository.existsById(id)){
            throw new ResourceNotFoundException("Hata : Silmek istediğiniz id bulunamadı");
        }

        userRepository.deleteById(id);
    }


}
