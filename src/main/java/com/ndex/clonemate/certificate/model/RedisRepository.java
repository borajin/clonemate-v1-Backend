package com.ndex.clonemate.certificate.model;

import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<RedisSession, Long> {
}
