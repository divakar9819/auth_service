package com.auth.indentity.config.security;

import com.auth.indentity.exception.security.CustomSecurityException;
import com.auth.indentity.utils.security.AccessToken;
import com.auth.indentity.utils.security.ITokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Divakar Verma
 * @created_at : 15/01/2024 - 11:25 am
 * @mail_to: vermadivakar2022@gmail.com
 */
@Component
public class TokenFilter extends OncePerRequestFilter {


    @Autowired
    private ITokenProvider tokenProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        AccessToken accessToken = tokenProvider.getTokenFromHeader(request);
        try {
            if(checkAccessToken(accessToken)){
                Authentication authentication = tokenProvider.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request,response);
        }
        catch (CustomSecurityException customSecurityException){
            SecurityContextHolder.clearContext();
            throw  customSecurityException;
        }
    }

    private boolean checkAccessToken(AccessToken accessToken){
        if(accessToken==null){
            return false;
        }
        return tokenProvider.validateToken(accessToken);
    }
}
