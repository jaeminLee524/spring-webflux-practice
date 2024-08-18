package com.study.webflux1practice.repository;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private final ConcurrentHashMap<Long, User> userHashMap = new ConcurrentHashMap<>();
    private AtomicLong sequence = new AtomicLong(1L);

    @Override
    public Mono<User> save(User user) {
        LocalDateTime now = LocalDateTime.now();
        if (user == null) {
            user.assignId(sequence.getAndAdd(1L));
            user.assignCreatedAt(now);
        }

        user.assignUpdatedAt(now);
        userHashMap.put(user.getId(), user);

        return Mono.just(user);
    }

    @Override
    public Flux<User> findAll() {
        return Flux.fromIterable(userHashMap.values());
    }

    @Override
    public Mono<User> findById(Long id) {
        return Mono.justOrEmpty(userHashMap.getOrDefault(id, null));
    }

    @Override
    public Mono<Integer> deleteById(Long id) {
        User findUser = userHashMap.getOrDefault(id, null);
        if (findUser == null) {
            return Mono.just(0);
        }

        userHashMap.remove(id);

        return Mono.just(1);
    }
}
