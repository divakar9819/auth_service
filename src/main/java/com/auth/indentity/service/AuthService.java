package com.auth.indentity.service;


import com.auth.indentity.payload.request.UserLoginRequest;
import com.auth.indentity.payload.request.UserRegisterRequest;
import com.auth.indentity.payload.response.TokenResponse;
import com.auth.indentity.payload.response.UserRegisterResponse;
import com.auth.indentity.utils.security.AccessToken;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Divakar Verma
 * @created_at : 10/01/2024 - 6:29 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
public interface AuthService {
    public UserRegisterResponse userRegistration(UserRegisterRequest userRegisterRequest);
    public AccessToken userLogin(UserLoginRequest userLoginRequest);

    public List<UserRegisterResponse> getAllUser();

    public Mono<TokenResponse> validateToken(String token);

}
