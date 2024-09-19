package com.study.webflux1practice.dto;

public record PostCreateRequest(
    Long userId,
    String title,
    String content
) {

}
