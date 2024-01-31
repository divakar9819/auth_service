package com.auth.indentity.utils.security;

import com.auth.indentity.entity.Role;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

import java.util.Set;

/**
 * @author Divakar Verma
 * @created_at : 11/01/2024 - 1:43 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
public interface ITokenProvider {

    public AccessToken createToken(String username, Set<Role> roles);
    public boolean validateToken(AccessToken accessToken);
    public AccessToken getTokenFromHeader(HttpServletRequest httpServletRequest);
    public Authentication getAuthentication(AccessToken accessToken);
    public String getUsernameFromToken(String token);
}
