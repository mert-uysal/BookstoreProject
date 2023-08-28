package com.example.bookstoreproject.service;

import java.sql.Timestamp;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bookstoreproject.RequestResponseDto.UserDto;
import com.example.bookstoreproject.entity.User;
import com.example.bookstoreproject.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(UserDto userDto) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        User user = User.builder()
            .name(userDto.getName())
            .email(userDto.getEmail())
            .password(passwordEncoder.encode(userDto.getPassword()))
            .role(userDto.getRole())
            .createdAt(currentTimestamp)
            .updatedAt(currentTimestamp)
            .build();

        return userRepository.save(user);
    }

}
