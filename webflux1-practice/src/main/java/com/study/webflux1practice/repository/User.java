package com.study.webflux1practice.repository;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table("users")
public class User {

    @Id
    private Long id;
    private String name;
    private String email;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
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

    public void assignName(String name) {
        this.name = name;
    }

    public void assignEmail(String email) {
        this.email = email;
    }
}
