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
import reactor.core.publisher.Flux;
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
    void findAllUsers() {
        LocalDateTime now = LocalDateTime.now();

        when(userService.findAll()).thenReturn(
            Flux.just(
                User.builder().id(1L).name("jm").email("fkdlem524@naver.com").createdAt(now).updatedAt(now).build(),
                User.builder().id(2L).name("jm2").email("hello524@naver.com").createdAt(now).updatedAt(now).build(),
                User.builder().id(3L).name("jm3").email("hihi@naver.com").createdAt(now).updatedAt(now).build()
            ));

        webTestClient.get().uri("/users")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBodyList(UserResponse.class)
            .hasSize(3);
    }

    @Test
    void findUser() {
        LocalDateTime now = LocalDateTime.now();

        when(userService.findById(1L)).thenReturn(
            Mono.just(User.builder().id(1L).name("jm").email("fkdlem524@naver.com").createdAt(now).updatedAt(now).build()
            ));

        webTestClient.get().uri("/users/1")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(UserResponse.class)
            .value(user -> {
                assertEquals(user.getName(), "jm");
                assertEquals(user.getEmail(), "fkdlem524@naver.com");
            });
    }

    @Test
    void deleteUser() {

    }

    @Test
    void upeateUser() {
    }
}