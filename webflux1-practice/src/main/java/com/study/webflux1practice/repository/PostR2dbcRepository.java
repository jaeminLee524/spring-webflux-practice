package com.study.webflux1practice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PostR2dbcRepository extends ReactiveCrudRepository<Post, Long> {

}
