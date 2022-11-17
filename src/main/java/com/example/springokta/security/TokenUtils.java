package com.example.springokta.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.xml.crypto.Data;
import java.util.*;

/**
 * @project: spring-okta
 * @author: rsaldanar
 * @date: 16/11/22
 * @Correo: rsaldanar@gmail.com
 * @description:
 * @history:
 */
public class TokenUtils {

        public static final String ACCESS_TOKEN_SECRET = "4qhq8LrEBfYcaRHxhdb9zURb2rf8e7Ud"; // TODO: 16/11/22  Change this to your own secret key for token encryption
        public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L; // 30 days

    public static String createToken(String nombre, String email) {
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }


    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
       try {
           Claims claims = Jwts.parserBuilder()
                   .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                   .build()
                   .parseClaimsJws(token)
                   .getBody();

           String email = claims.getSubject();
           return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
       }catch (JwtException e){
           return null;
       }
    }



}
