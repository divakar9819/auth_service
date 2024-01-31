package com.auth.indentity.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Divakar Verma
 * @created_at : 10/01/2024 - 6:35 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserLoginRequest {
    private String username;
    private String password;
}
