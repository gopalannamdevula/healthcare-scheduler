package com.gopal.appointment.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {

    private final String secretKey = "secretskjnjnkjnd-vn-mvnjnjnjnbvnjbvjdjbvjnvjnjb";

    private Key getSigningKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

        public Claims getClaims(String token) {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
        public String getUsername(String token) {
           return getClaims(token).getSubject();
        }

        public String getUserId(String token) {
          return getClaims(token).get("userId",String.class);
        }

        public boolean validateToken(String token) {
         return !getClaims(token).getExpiration().before(new Date());
        }


}
