package com.auth.indentity.exception.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Divakar Verma
 * @created_at : 11/01/2024 - 3:53 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
@Getter
@Setter
public class CustomSecurityException extends  RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    public CustomSecurityException(String message){
        super(message);
        this.message = message;
    }

    public CustomSecurityException(String message, HttpStatus httpStatus){
        super(String.format("message : %s and Status code : %s ",message,httpStatus));
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
