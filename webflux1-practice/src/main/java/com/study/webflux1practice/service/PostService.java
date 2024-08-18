package com.study.webflux1practice.service;

import com.study.webflux1practice.client.PostClient;
import com.study.webflux1practice.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostClient postClient;

    public Mono<PostResponse> getPostContent(Long id) {
        return postClient.getPost(id)
            .log();
    }
}
