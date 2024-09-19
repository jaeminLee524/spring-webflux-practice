package com.study.webflux1practice.controller;

import com.study.webflux1practice.dto.PostCreateRequest;
import com.study.webflux1practice.dto.PostResponseV2;
import com.study.webflux1practice.service.PostServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v2/posts")
public class PostControllerV2 {

    private final PostServiceV2 postServiceV2;

    @PostMapping("")
    public Mono<PostResponseV2> createPost(
        @RequestBody PostCreateRequest request
    ) {
        return postServiceV2.create(request.userId(), request.title(), request.content())
            .map(PostResponseV2::of);
    }

    @GetMapping("")
    public Flux<PostResponseV2> findAllPosts() {
        return postServiceV2.findAll()
            .map(PostResponseV2::of);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PostResponseV2>> findAllPosts(
        @PathVariable Long id
    ) {
        return postServiceV2.findById(id)
            .map(p -> ResponseEntity.ok().body(PostResponseV2.of(p)))
            .switchIfEmpty (Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<PostResponseV2>> deletePost(
        @PathVariable Long id
    ) {
        return postServiceV2.deleteById(id).then(Mono.just(ResponseEntity.noContent().build()));
    }
}
