package com.example.advanced_testing.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.advanced_testing.entity.User;
import com.example.advanced_testing.entity.UserStatus;
import com.example.advanced_testing.repository.UserRepository;

import autoparams.AutoSource;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        when(userRepository.save(Mockito.any(User.class))).thenAnswer(i -> Mono.just(i.getArguments()[0]));
    }

    @ParameterizedTest
    @AutoSource
    void 사용자_가입_테스트(String name, @Min(20) @Max(99) int age) {
        StepVerifier.create(userService.registerUser(name, age))
            .expectNextMatches(user -> user.getAge() == age &&
                user.getName().equals(name) &&
                UserStatus.ACTIVE.equals(user.getStatus()))
            .verifyComplete();
    }

}
