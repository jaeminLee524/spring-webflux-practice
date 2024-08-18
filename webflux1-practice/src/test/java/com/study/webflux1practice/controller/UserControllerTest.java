package com.study.webflux1practice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.study.webflux1practice.dto.UserCreateRequest;
import com.study.webflux1practice.dto.UserResponse;
import com.study.webflux1practice.repository.User;
import com.study.webflux1practice.service.UserService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(UserController.class)
@AutoConfigureWebTestClient
class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @Test
    void createUser() {
        LocalDateTime now = LocalDateTime.now();

        when(userService.create("jm", "fkdlem524@naver.com")).thenReturn(
            Mono.just(User.builder().id(1L).name("jm").email("fkdlem524@naver.com").createdAt(now).updatedAt(now).build()
            ));

        webTestClient.post().uri("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UserCreateRequest("jm", "fkdlem524@naver.com"))
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(UserResponse.class)
            .value(user -> {
                assertEquals(user.getName(), "jm");
                assertEquals(user.getEmail(), "fkdlem524@naver.com");
            });
    }

    @Test
    void findAll() {
    }

    @Test
    void findUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void upeateUser() {
    }
}