package com.qas.empworld.security;

import com.qas.empworld.model.JwtUser;
import com.qas.empworld.model.UserMaster;
import com.qas.empworld.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtGenerator {

    private long EXPIRATIONTIME = 1000*60*60*168 ; // 12 Hours
    @Autowired
    UserRepository userRepository;

    public String generate(JwtUser jwtUser) {
        Claims claims = Jwts.claims()
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getUserId()));
        claims.put("role", jwtUser.getRole());
        claims.put("institutionId",String.valueOf(jwtUser.getInstitutionId()));

        Optional<UserMaster> um = userRepository.findById(jwtUser.getUserId());
        um.get().setRecentActivityTime(new Date().getTime());
        userRepository.save(um.get());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "youtube")
                .compact();


    }
}