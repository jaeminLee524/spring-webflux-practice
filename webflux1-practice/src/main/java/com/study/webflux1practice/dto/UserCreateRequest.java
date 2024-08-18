package com.study.webflux1practice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UserCreateRequest {

    private String name;
    private String email;
}
