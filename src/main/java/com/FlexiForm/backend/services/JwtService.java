package com.FlexiForm.backend.services;

import com.FlexiForm.backend.entities.UserEntity;
import com.nimbusds.openid.connect.sdk.claims.ClaimsSet;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey(){ return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));}

    public String generateAccessToken(UserEntity userEntity){

        return Jwts.builder()
                .subject(userEntity.getId().toString())
                .claim("email", userEntity.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*10))
                .signWith(getSecretKey())
                .compact();
    }


    public String generateRefreshToken(UserEntity userEntity){
        return Jwts.builder()
                .subject(userEntity.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000L * 60 *60 *24 *30* 6))
                .signWith(getSecretKey())
                .compact();
    }

    public Long getUserIdFromToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return Long.valueOf(claims.getSubject());
    }



}
