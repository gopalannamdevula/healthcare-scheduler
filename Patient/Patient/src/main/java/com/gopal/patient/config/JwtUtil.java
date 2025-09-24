package com.gopal.patient.config;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


//This class is a utility that:
//Creates JWT tokens.
//Extracts information from tokens.
//Validates tokens.

@Component
public class JwtUtil {

    //This is the shared secret used to sign and later verify JWTs.
    //It must be at least 256 bits (~32 characters) to work with HS256 securely.
    private final String secretKey = "secretskjnjnkjnd-vn-mvnjnjnjnbvnjbvjdjbvjnvjnjb";

    // 🔐 Convert string secret to secure Key object
    // Keys.hmacShaKeyFor(...) ensures the key is valid for HMAC algorithms like HS256.
    private Key getSigningKey(){
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String generateToken(UserDetails principal, String userId){

        return Jwts.builder()
                .setSubject(principal.getUsername())  //who the token is for
                .claim("userId", userId)
                .setIssuedAt(new Date(System.currentTimeMillis())) // when the token created
                .setExpiration(new Date(System.currentTimeMillis()+600000)) // when it will expire
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // sign using key + algorithm
                .compact();  // build final token
    }

    //parserBuilder() creates a fresh parser with custom config.
    //setSigningKey() ensures the signature is valid before reading data.
    //getSubject() reads the username stored in the token.

    public String extractUsername(String token){

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }
    public boolean isTokenExpired(String token){
        Date expiry = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
         return expiry.before(new Date());

    }

    public boolean validateToken(String token, UserDetails userDetails){
        String userName = extractUsername(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String extractUserIdFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("userId", String.class);
    }

}
