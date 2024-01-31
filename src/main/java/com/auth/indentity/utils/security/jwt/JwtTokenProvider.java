package com.auth.indentity.utils.security.jwt;

import com.auth.indentity.entity.Role;
import com.auth.indentity.utils.security.AccessToken;
import com.auth.indentity.utils.security.ITokenProvider;
import com.auth.indentity.utils.security.SecretKey;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author Divakar Verma
 * @created_at : 16/01/2024 - 11:23 am
 * @mail_to: vermadivakar2022@gmail.com
 */
@Component
public class JwtTokenProvider implements ITokenProvider {

    @Value("${security.jwt.token.security-kye}")
    private String secretKeyValue;
    @Value("${security.jwt.token.expiration}")
    private long expiration;

    @Autowired
    private IJwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;

    private static String username;

    @Override
    public AccessToken createToken(String username, Set<Role> roles) {
        SecretKey secretKey = new SecretKey(secretKeyValue, expiration);
        String token = jwtTokenHelper.generateJwtToken(secretKey, username, roles);
        AccessToken accessToken = new AccessToken("Login successfully", token, expiration);
        return accessToken;
    }

    @Override
    public boolean validateToken(AccessToken accessToken) {
        SecretKey secretKey = new SecretKey(secretKeyValue, expiration);
        return jwtTokenHelper.validateJwtToken(secretKey, accessToken);
    }

    @Override
    public AccessToken getTokenFromHeader(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader("Authorization");
        if (bearerToken == null) return null;
        if (!bearerToken.startsWith("Bearer ")) return null;
        return new AccessToken(bearerToken.substring(7));
    }

    @Override
    public Authentication getAuthentication(AccessToken accessToken) {
        SecretKey secretKey = new SecretKey(secretKeyValue, expiration);
        username = jwtTokenHelper.getUsernameFromJwtToken(secretKey, accessToken);
        System.out.println(username);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        return authentication;
    }

    public  String getUsernameFromToken(String token){
        SecretKey secretKey = new SecretKey(secretKeyValue, expiration);
        AccessToken accessToken = new AccessToken(token);
        return jwtTokenHelper.getUsernameFromJwtToken(secretKey,accessToken);
    }

    public static String getUsername(){
        return username;
    }
}
