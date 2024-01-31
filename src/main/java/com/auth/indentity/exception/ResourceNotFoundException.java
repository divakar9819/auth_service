package com.auth.indentity.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Divakar Verma
 * @created_at : 10/01/2024 - 5:57 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue){
        super(String.format("%s is not found %s : %s",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
