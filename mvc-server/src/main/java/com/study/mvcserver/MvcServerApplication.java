package com.study.mvcserver;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MvcServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcServerApplication.class, args);
    }

    @GetMapping("/posts/{id}")
    public Map<String, String> gertPosts(
        @PathVariable("id") Long id
    ) throws Exception {
        Thread.sleep(2000);

        if (id > 10L) {
            throw new Exception("id too long");
        }

        return Map.of("id", id.toString(), "content", "Post content is %d".formatted(id));
    }
}
