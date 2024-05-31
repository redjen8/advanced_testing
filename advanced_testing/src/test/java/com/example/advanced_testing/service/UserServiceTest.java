package com.example.advanced_testing.service;

import static org.assertj.core.api.BDDAssertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;

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
    void 사용자_가입_테스트_autoParams(String name, @Min(20) @Max(99) int age) {
        StepVerifier.create(userService.registerUser(name, age))
            .expectNextMatches(user -> user.getAge() == age &&
                user.getName().equals(name) &&
                UserStatus.ACTIVE.equals(user.getStatus()))
            .verifyComplete();
    }

    @Test
    void 사용자_가입_테스트_fixtureMonkey() {
        FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
            .build();

        final Integer age = 26;

        User user = fixtureMonkey.giveMeBuilder(User.class)
            .set("age", 26)
            .sample();

        System.out.println(user);

        then(user.getAge()).isEqualTo(26);
    }

}
