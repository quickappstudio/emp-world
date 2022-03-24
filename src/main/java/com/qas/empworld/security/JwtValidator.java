package com.qas.empworld.security;

import com.qas.empworld.model.BlockedTokenMaster;
import com.qas.empworld.model.JwtUser;
import com.qas.empworld.model.UserMaster;
import com.qas.empworld.repository.BlockedTokenRepository;
import com.qas.empworld.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


@Component
public class JwtValidator {
    private String secret = "youtube";

    @Autowired
    UserRepository userRepository;

    @Autowired
    BlockedTokenRepository blockedTokenRepository;

    public JwtUser validate(String token) {
        BlockedTokenMaster btm = blockedTokenRepository.findByToken(token);
        if (btm == null) {
            JwtUser jwtUser = null;
            try {
                Claims body = Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token)
                        .getBody();
                Optional<UserMaster> um = userRepository.findById(body.get("userId").toString());
                long currentTime = System.currentTimeMillis();
                long fixedExpireTime = 1200000;
                long difference = (currentTime - um.get().getRecentActivityTime());
                if (difference < fixedExpireTime){
                    jwtUser = new JwtUser();
                    jwtUser.setUserId((String) body.get("userId"));
                    jwtUser.setUserName(body.getSubject());
                    jwtUser.setRole((String) body.get("role"));
                    jwtUser.setInstitutionId((String) body.get("institutionId"));
                    jwtUser.setExpiration(System.currentTimeMillis());
                    um.get().setRecentActivityTime(new Date().getTime());
                    userRepository.save(um.get());
                }else{
                   BlockedTokenMaster bt = new BlockedTokenMaster();
                    bt.setToken(token);
                    bt.setCreatedDate(fixedExpireTime);
                    blockedTokenRepository.save(bt);
                    return null;
                }
            }catch (Exception e) {
                System.out.println(e);
            }
            return jwtUser;
        }else {
            return null;
        }
    }
}
