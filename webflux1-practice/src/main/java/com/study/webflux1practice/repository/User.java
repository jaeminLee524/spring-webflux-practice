package com.study.webflux1practice.repository;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void assignId(long id) {
        this.id = id;
    }

    public void assignCreatedAt(LocalDateTime now) {
        this.createdAt = now;
    }

    public void assignUpdatedAt(LocalDateTime now) {
        this.updatedAt = now;
    }
}
