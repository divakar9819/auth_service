package com.auth.indentity.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Divakar Verma
 * @created_at : 17/01/2024 - 12:03 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserRegisterResponse {
    private long id;
    private String username;
    private String email;
    private Set<RoleResponse> roleResponses = new HashSet<>();
}
