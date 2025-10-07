package com.spcodage.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class JwtService1 {

        private static final String SECRET_KEY = "YXNzc3NoaGRhc3Nzc2hoZGFzc3Nzc2hoZGFzc3Nzc2hoZGE=";
        private static final long ACCESS_TOKEN_EXPIRATION = 5 * 60 * 1000; // 5 min
        private static final long REFRESH_TOKEN_EXPIRATION = 24 * 60 * 60 * 1000; // 24 hours

        private SecretKey getKey() {
            return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
        }

        // Generate access or refresh token
        public String generateToken(CustomUserDetails customUser, boolean isAccessToken) {
            long expirationMs = isAccessToken ? ACCESS_TOKEN_EXPIRATION : REFRESH_TOKEN_EXPIRATION;
            Instant now = Instant.now();

            List<String> roles = customUser.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .map(auth -> auth.replace("ROLE_", "")) // strip prefix
                    .toList();

              // Build claims
            Claims claims = Jwts.claims()
                    .add("email", customUser.getUsername())
                    .add("roles", roles)
                    .build();

            return Jwts.builder()
                    .claims(claims)
                    .subject(customUser.getUsername())
                    .issuedAt(Date.from(now))
                    .expiration(Date.from(now.plusMillis(expirationMs)))
                    .signWith(getKey())
                    .compact();
        }

        // Extract username from token
        public String getUsernameFromToken(String token) {
            Jws<Claims> jwsClaims = Jwts.parser()   // parser() returns builder in 0.12.6
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token);       // parse token safely

            return jwsClaims.getPayload().getSubject();
        }

        // Token validation (signature + expiry)
        public boolean validateToken(String token) {
            try {
                Jwts.parser()
                        .verifyWith(getKey())
                        .build()
                        .parseSignedClaims(token);
                        return true;

            } catch (JwtException ex) {
                return false;
            }
        }


        // Token validation with username check
        public boolean validateToken(String token, String username) {
            try {
                String tokenUsername = getUsernameFromToken(token);
                return tokenUsername.equals(username) && !isTokenExpired(token);
            } catch (JwtException ex) {
                return false;
            }
        }

        // Check if token expired
        private boolean isTokenExpired(String token) {
            try {
                Jws<Claims> jwsClaims = Jwts.parser()
                        .verifyWith(getKey())
                        .build()
                        .parseSignedClaims(token);

                Date expiration = jwsClaims.getPayload().getExpiration();
                return expiration.before(new Date());
            } catch (JwtException ex) {
                return true;
            }
        }


    }

