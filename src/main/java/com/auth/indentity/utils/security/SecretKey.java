package com.auth.indentity.utils.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Divakar Verma
 * @created_at : 11/01/2024 - 1:46 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SecretKey {

    private String secretKey;
    private long expirationInMiliSeconds;
}
