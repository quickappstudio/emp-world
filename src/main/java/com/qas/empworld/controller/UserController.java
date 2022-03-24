package com.qas.empworld.controller;

import com.qas.empworld.model.JwtUser;
import com.qas.empworld.model.UserMaster;
import com.qas.empworld.security.JwtValidator;
import com.qas.empworld.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/user")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    private JwtValidator jwtValidator;

    private static class LoginBean {
        public String loginId;
        public String password;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/get")
    public ResponseEntity get() {
        return ResponseEntity.ok("Success");
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/registerUser")
    public ResponseEntity registerUser(
//            @RequestHeader(value = "Authorization") String authToken,
            @RequestBody UserMaster request) throws IllegalAccessException {
//        JwtUser jwtUserDetails = jwtValidator.validate(authToken.substring(6));
//        if (jwtUserDetails == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Denied");
//        }
//        if (request == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
//        }
        System.out.println("test");
        String response = userService.registerUser(request, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/signIn")
    public ResponseEntity signIn(@RequestBody LoginBean loginBean) {
        if (loginBean.loginId == null || loginBean.password == null)
            return ResponseEntity.badRequest().body("Bad Request");
        String response = userService.validateAndGenerateToken(loginBean.loginId, loginBean.password);
        if (response.equals("USER_NOT_FOUND")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User details not found");
        }
        if (response.equals("INVALID_PASSWORD")) {
            return ResponseEntity.status(FORBIDDEN).body("Invalid Password");
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/updateUser")
    public ResponseEntity updateUser(
            @RequestHeader(value = "Authorization") String authToken,
            @RequestBody UserMaster request) {
        JwtUser jwtUserDetails = jwtValidator.validate(authToken.substring(6));
        if (jwtUserDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Denied");
        }
        if (request == null || request.getUserId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
        }
        logger.info(String.format("Modified the user details -%s", toString()));
        String response = userService.updateUser(request, jwtUserDetails.getUserId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/signOut")
    public ResponseEntity signOut (
            @RequestHeader(value = "Authorization") String authToken) {
        JwtUser jwtUserDetails = jwtValidator.validate(authToken.substring(6));
        if (jwtUserDetails == null) {
            return ResponseEntity.status(HttpStatus.OK).body("Sign out successfully");
        }
        String response = userService.blockToken(authToken.substring(6), jwtUserDetails.getExpiration());
        return ResponseEntity.ok(response);
    }
}
