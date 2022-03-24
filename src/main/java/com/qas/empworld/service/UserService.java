package com.qas.empworld.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qas.empworld.dto.response.GeneralResponse;
import com.qas.empworld.model.BlockedTokenMaster;
import com.qas.empworld.model.JwtUser;
import com.qas.empworld.model.UserMaster;
import com.qas.empworld.repository.BlockedTokenRepository;
import com.qas.empworld.repository.UserRepository;
import com.qas.empworld.security.JwtGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    BlockedTokenRepository blockedTokenRepository;


    private JwtGenerator jwtGenerator;

    public UserService(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }


    public String registerUser(UserMaster userMaster, String actor) throws IllegalAccessException {
        String encryptedPassword = new BCryptPasswordEncoder().encode(userMaster.getPassword());
        UserMaster um = new UserMaster();
        um.setPassword(encryptedPassword);
        um.setPrimaryContact(userMaster.getPrimaryContact());
        um.setPersonalEmail(userMaster.getPersonalEmail());
        um.setLoginId(userMaster.getLoginId());
        um.setInstitutionId(userMaster.getInstitutionId());
        um.setSalutationId(userMaster.getSalutationId());
        um.setRole(userMaster.getRole());
        um.setFirstName(userMaster.getFirstName());
        um.setSurName(userMaster.getSurName());
        um.setLastName(userMaster.getLastName());
        um.setPasswordResetDone(false);
        um.setLastLoginTime(null);
        um.setCreatedBy(actor);
        um.setCreatedDate(new Date().getTime());
        userRepository.save(um);
        return new Gson().toJson(new GeneralResponse("Registered Successfully", null));
    }

    public String updateUser(UserMaster request, String userId){
        Gson gson= new Gson();
        Optional<UserMaster> umo = userRepository.findById(request.getUserId());
        if (umo.isPresent()){
            UserMaster um = umo.get();
            if (request.getRole() != null)
                um.setRole(request.getRole());
            if (request.getPrimaryContact() != null)
                um.setPrimaryContact(request.getPrimaryContact());
            if (request.getPersonalEmail() != null)
                um.setPersonalEmail(request.getPersonalEmail());
            if (request.getInstitutionId() != null)
                um.setInstitutionId(request.getInstitutionId());
            if (request.getSalutationId() != null)
                um.setSalutationId(request.getSalutationId());
            um.setLastUpdateUserId(userId);
            um.setLastUpdateDate(new Date().getTime());
            userRepository.save(um);
            logger.info(String.format("Modified the user details -%s", toString()));
            GeneralResponse gr = new GeneralResponse("User details modified successfully", null);
            return gson.toJson(gr);
        } else {
            return gson.toJson(new GeneralResponse("Could not find user details",null));
        }
    }

    public String validateAndGenerateToken(String loginId, String password) {
        UserMaster userMaster = userRepository.findByLoginId(loginId);
        if (userMaster == null) {
            return "USER_NOT_FOUND";
        }

        userMaster.setLastLoginTime(System.currentTimeMillis());
        userRepository.save(userMaster);
        Gson gson = new Gson();
        if (!new BCryptPasswordEncoder().matches(password, userMaster.getPassword())) {
            return "INVALID_PASSWORD";
        }

        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserId(userMaster.getUserId());
        jwtUser.setRole(userMaster.getRole());
        jwtUser.setUserName(userMaster.getFirstName());
        jwtUser.setInstitutionId(userMaster.getInstitutionId());
        String token = jwtGenerator.generate(jwtUser);

        JsonObject jsonObject = gson.toJsonTree(userMaster).getAsJsonObject();
        jsonObject.remove("password");
        jsonObject.add("role", gson.toJsonTree(userMaster.getRole()));
        jsonObject.add("token", gson.toJsonTree(token));
        return gson.toJson(new GeneralResponse("Login Successfully", jsonObject));
    }

    public String blockToken(String token,Long expiryDate) {
        BlockedTokenMaster btm = new BlockedTokenMaster();
        btm.setToken(token);
        btm.setCreatedDate(expiryDate);
        blockedTokenRepository.save(btm);
        return new Gson().toJson(new GeneralResponse("Sign out successfully", null));
    }
}
