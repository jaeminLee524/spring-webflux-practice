package com.study.webflux1practice.service;

import com.study.webflux1practice.client.PostClient;
import com.study.webflux1practice.dto.PostResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostClient postClient;

    public Mono<PostResponse> getPostContent(Long id) {
        return postClient.getPost(id)
            .log();
    }

    public Flux<PostResponse> getMultiplePostContent(List<Long> idList) {
        return Flux.fromIterable(idList)
            .flatMap(this::getPostContent);
    }

    public Flux<PostResponse> getParallelMultiplePostContent(List<Long> idList) {
        return Flux.fromIterable(idList)
            .parallel()
            .runOn(Schedulers.parallel())
            .flatMap(this::getPostContent)
            .log()
            .sequential();
    }
}
