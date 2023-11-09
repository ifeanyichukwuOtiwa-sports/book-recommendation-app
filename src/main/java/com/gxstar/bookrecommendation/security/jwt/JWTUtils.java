package com.gxstar.bookrecommendation.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
public class JWTUtils {

    @Value(value = "${book-recommendation.jwt.secret}")
    private String jwtSecret;
    @Value(value = "${book-recommendation.jwt.expiration-ms}")
    private String jwtExpirationMs;
    @Value(value = "${book-recommendation.jwt.cookie}")
    private String jwtCookie;

    public String getJwtFromCookies(final HttpServletRequest request) {
        final Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        return Objects.isNull(cookie) ? null : cookie.getValue();
    }

    public ResponseCookie generateJwtCookieFromUsername(final String username) {
        final String jwt = generateTokenFromUsername(username);
        return ResponseCookie.from(jwtCookie, jwt)
                .path("/api")
                .maxAge(Duration.ofDays(1))
                .httpOnly(true)
                .build();
    }

    private String generateTokenFromUsername(final String username) {
        final Date issuedAt = Date.from(Instant.now());
        final SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        return Jwts.builder()
                .header()
                .add("x-star-temp", "temp")
                .and()
                .subject(username)
                .issuedAt(issuedAt)
                .expiration(DateUtils.addMilliseconds(issuedAt, Integer.parseInt(jwtExpirationMs)))
                .signWith(secretKey)
                .compact();
    }

    public ResponseCookie generateJwtCookie() {
        return ResponseCookie.from(jwtCookie)
                .path("/api")
                .build();
    }

    public String generateUsernameFromToken(final String token) {
        final SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateJwtToken(final String jwtToken) {
        try {
            final SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(jwtToken);
            return true;
        }  catch (RuntimeException e) {
            log.error("JWT Validation failed: {}", e.getMessage(), e);
        }
        return false;
    }
}
