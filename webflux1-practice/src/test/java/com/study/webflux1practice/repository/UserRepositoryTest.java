package com.study.webflux1practice.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.study.webflux1practice.dto.UserResponse;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class UserRepositoryTest {

    private final UserRepository userRepository = new UserRepositoryImpl();

    @Test
    void save() {
        User user = User.builder()
            .id(1L)
            .name("jm")
            .email("fkdlem524@naver.com")
            .build();

        StepVerifier.create(userRepository.save(user))
            .assertNext(u -> {
                assertEquals(u.getId(), 1L);
                assertEquals(u.getName(), "jm");
                assertEquals(u.getEmail(), "fkdlem524@naver.com");
            })
            .verifyComplete();
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void deleteById() {
    }
}