package com.example.advanced_testing.service;

import org.springframework.stereotype.Service;

import com.example.advanced_testing.entity.User;
import com.example.advanced_testing.entity.UserStatus;
import com.example.advanced_testing.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Mono<User> registerUser(String name, Integer age) {
        return userRepository.save(
            User.builder()
                .name(name)
                .age(age)
                .status(UserStatus.ACTIVE)
                .build()
        );
    }

    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }
}
