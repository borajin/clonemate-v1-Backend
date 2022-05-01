package com.ndex.clonemate.certificate.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@RedisHash(value = "user")
public class RedisSession {
    @Id
    private long id;
    private String key;
    private LocalDateTime updatedAt;

    public RedisSession(long id, String key) {
        this.id = id;
        this.key = key;
        this.updatedAt = LocalDateTime.now();
    }
}
