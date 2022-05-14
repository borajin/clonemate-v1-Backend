package com.ndex.clonemate.global.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtils {
    @Value("${jwt.token.key}")
    private String SECRET_KEY;

    public String createToken(String id, String issuer, String subject, long ttlMillis) {
        long nowMillis = System.currentTimeMillis();

        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(new Date(nowMillis))
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(getSignKey())
                .setExpiration(new Date(nowMillis + ttlMillis));

        return builder.compact();
    }

    public Jws<Claims> parseJwtToken(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(jwt);
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public boolean isValidExpiration(Jws<Claims> jwsToken) {
        Date expiration = jwsToken.getBody().getExpiration();
        Date now = Timestamp.valueOf(LocalDateTime.now());
        return expiration.before(now);
    }
}
