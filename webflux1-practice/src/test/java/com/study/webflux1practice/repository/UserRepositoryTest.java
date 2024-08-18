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
        User user1 = User.builder()
            .id(1L)
            .name("jm1")
            .email("jm1@naver.com")
            .build();
        User user2 = User.builder()
            .id(2L)
            .name("jm2")
            .email("jm2@naver.com")
            .build();
        User user3 = User.builder()
            .id(3L)
            .name("jm3")
            .email("jm3@naver.com")
            .build();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        StepVerifier.create(userRepository.findAll())
            .expectNextCount(3)
            .verifyComplete();
    }

    @Test
    void findById() {
        User user1 = User.builder()
            .id(1L)
            .name("jm1")
            .email("jm1@naver.com")
            .build();
        User user2 = User.builder()
            .id(2L)
            .name("jm2")
            .email("jm2@naver.com")
            .build();

        userRepository.save(user1);
        userRepository.save(user2);

        StepVerifier.create(userRepository.findById(1L))
            .assertNext(user -> {
                assertEquals(user.getId(), 1L);
                assertEquals(user.getName(), "jm1");
                assertEquals(user.getEmail(), "jm1@naver.com");
            })
            .verifyComplete();
    }

    @Test
    void deleteById() {

    }
}