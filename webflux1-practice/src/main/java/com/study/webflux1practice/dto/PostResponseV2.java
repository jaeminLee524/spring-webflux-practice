package com.study.webflux1practice.dto;

import com.study.webflux1practice.repository.Post;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record PostResponseV2(
    Long id,
    Long userId,
    String title,
    String content,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static PostResponseV2 of(Post post) {
        return PostResponseV2.builder()
            .id(post.getId())
            .userId(post.getUserId())
            .title(post.getTitle())
            .content(post.getContent())
            .createdAt(post.getCreatedAt())
            .updatedAt(post.getUpdatedAt())
            .build();
    }
}
