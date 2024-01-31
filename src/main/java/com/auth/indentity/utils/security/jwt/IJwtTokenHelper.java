package com.auth.indentity.utils.security.jwt;


import com.auth.indentity.entity.Role;
import com.auth.indentity.utils.security.AccessToken;
import com.auth.indentity.utils.security.SecretKey;

import java.util.Set;

/**
 * @author Divakar Verma
 * @created_at : 16/01/2024 - 11:18 am
 * @mail_to: vermadivakar2022@gmail.com
 */
public interface IJwtTokenHelper {
    public String generateJwtToken(SecretKey secretKey, String username, Set<Role> roles);
    public boolean validateJwtToken(SecretKey secretKey, AccessToken accessToken);
    public String getUsernameFromJwtToken(SecretKey secretKey, AccessToken accessToken);
}
