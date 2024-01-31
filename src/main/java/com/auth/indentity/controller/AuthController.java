package com.auth.indentity.controller;

import com.auth.indentity.payload.request.UserLoginRequest;
import com.auth.indentity.payload.request.UserRegisterRequest;
import com.auth.indentity.payload.response.TokenResponse;
import com.auth.indentity.payload.response.UserRegisterResponse;
import com.auth.indentity.service.AuthService;
import com.auth.indentity.utils.security.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Divakar Verma
 * @created_at : 11/01/2024 - 10:51 am
 * @mail_to: vermadivakar2022@gmail.com
 */

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService userService;

    private String token;

    @GetMapping("/home")
    public String home(){
        return "user home";
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> createUser(@RequestBody UserRegisterRequest userRegisterDto){
        UserRegisterResponse createdUser = userService.userRegistration(userRegisterDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AccessToken> login(@RequestBody UserLoginRequest userLoginDto){
        AccessToken accessToken = userService.userLogin(userLoginDto);
        this.token = accessToken.getAccessToken();
        return new ResponseEntity<>(accessToken,HttpStatus.OK);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserRegisterResponse>> getAllUser(){
        List<UserRegisterResponse> userRegisterResponses = userService.getAllUser();
        return new ResponseEntity<>(userRegisterResponses,HttpStatus.OK);
    }

    @GetMapping("/getToken")
    public ResponseEntity<String> getToken(){
        return new ResponseEntity<>(this.token,HttpStatus.OK);
    }

    @PostMapping("/validateToken")
    public Mono<ResponseEntity<TokenResponse>> validateToken(@RequestBody String token){
        return userService.validateToken(token)
                .map(apiResponse -> new ResponseEntity<>(apiResponse,HttpStatus.OK));
    }
}
