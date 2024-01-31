package com.auth.indentity.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Divakar Verma
 * @created_at : 11/01/2024 - 1:23 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
@Setter
@Getter
public class UserNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public UserNotFoundException(String resourceName, String fieldName, String fieldValue){
        super(String.format("%s is not found %s : %s",resourceName,fieldName,fieldValue));
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public UserNotFoundException(String message){
        super(message);
    }
}
