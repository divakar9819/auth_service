package com.auth.indentity.utils.security.jwt;


import com.auth.indentity.entity.Role;
import com.auth.indentity.utils.security.AccessToken;
import com.auth.indentity.utils.security.SecretKey;
import io.jsonwebtoken.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Divakar Verma
 * @created_at : 16/01/2024 - 11:19 am
 * @mail_to: vermadivakar2022@gmail.com
 */
@Component
public class JwtsTokenHelper implements IJwtTokenHelper{
    @Override
    public String generateJwtToken(SecretKey secretKey, String username, Set<Role> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("authorities",roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
        Date issueAt = new Date();
        Date validUntil = new Date(issueAt.getTime()+secretKey.getExpirationInMiliSeconds());
        String secretKeyEncoded = Base64.getEncoder().encodeToString(secretKey.getSecretKey().getBytes());
        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(issueAt)
                .setExpiration(validUntil)
                .signWith(SignatureAlgorithm.HS256,secretKeyEncoded)
                .compact();
    }

    @Override
    public boolean validateJwtToken(SecretKey secretKey, AccessToken accessToken) {
        try {
            String secretKeyEncoded = Base64.getEncoder().encodeToString(secretKey.getSecretKey().getBytes());
            Claims claims = Jwts.parser().setSigningKey(secretKeyEncoded).parseClaimsJws(accessToken.getAccessToken()).getBody();
            Date expiration = claims.getExpiration();
            Date now = new Date();
            if (expiration != null && expiration.before(now)) {
                // Token is expired
                System.out.println("Token expired");
            }
            return true;
        }
        catch (JwtException | IllegalArgumentException exception){
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%55");
            throw exception;

        }
    }

    @Override
    public String getUsernameFromJwtToken(SecretKey secretKey, AccessToken accessToken) {
        String secretKeyEncoded = Base64.getEncoder().encodeToString(secretKey.getSecretKey().getBytes());
        try {
            String username = Jwts.parser().setSigningKey(secretKeyEncoded).parseClaimsJws(accessToken.getAccessToken()).getBody().getSubject();
            if (username == null) {
                System.out.println("null username");
            } else {
                System.out.println(username);
                return username;
            }
        } catch (ExpiredJwtException ex) {
            // Handle expired token
            System.out.println("Token has expired");
        } catch (Exception e) {
            // Handle other JWT exceptions
            System.out.println("Error parsing JWT: " + e.getMessage());
        }

       // Handle the case where the username is null or an exception occurred
        return null;
    }

}
