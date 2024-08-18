package com.study.webflux1practice.controller;

import com.study.webflux1practice.dto.UserCreateRequest;
import com.study.webflux1practice.dto.UserResponse;
import com.study.webflux1practice.dto.UserUpdateRequest;
import com.study.webflux1practice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public Mono<UserResponse> createUser(
        @RequestBody UserCreateRequest request
    ) {
        return userService.create(request.getName(), request.getEmail())
            .map(UserResponse::of);
    }

    @GetMapping("")
    public Flux<UserResponse> findAll() {
        return userService.findAll()
            .map(UserResponse::of);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserResponse>> findUser(
        @PathVariable Long id
    ) {
        return userService.findById(id)
            .map(u -> ResponseEntity.ok(UserResponse.of(u)))
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<?>> deleteUser(
        @PathVariable Long id
    ) {
        return userService.deleteById(id).then(Mono.just(ResponseEntity.noContent().build()));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<UserResponse>> upeateUser(
        @PathVariable("id") Long id,
        @RequestBody UserUpdateRequest request
    ) {
        return userService.update(id, request.getName(), request.getEmail())
            .map(u -> ResponseEntity.ok(UserResponse.of(u)))
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
}
