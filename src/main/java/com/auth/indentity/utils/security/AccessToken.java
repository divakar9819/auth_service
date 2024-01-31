package com.auth.indentity.utils.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Divakar Verma
 * @created_at : 10/01/2024 - 6:32 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccessToken {

    private String message;
    private String accessToken;
    private long expiresIn;

    public AccessToken(String accessToken){
        this.accessToken = accessToken;
    }
}
