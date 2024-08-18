package com.study.webflux1practice.controller;

import com.study.webflux1practice.dto.PostResponse;
import com.study.webflux1practice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public Mono<PostResponse> getPostContent(
        @PathVariable Long id
    ) {
        return postService.getPostContent(id);
    }
}
