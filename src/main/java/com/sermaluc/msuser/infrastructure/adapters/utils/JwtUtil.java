package com.sermaluc.msuser.infrastructure.adapters.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.sermaluc.msuser.domain.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
    
    private final String secretKey = "sermaluc";
    private long accessTokenValidity = 60*60*1000;

    public String createToken(User user) {
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("name",user.getName());

        return Jwts.builder()
            .setClaims(claims)
            .setExpiration(tokenValidity)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

}
