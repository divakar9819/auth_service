package com.auth.indentity.payload.response;

/**
 * @author Divakar Verma
 * @created_at : 17/01/2024 - 12:07 pm
 * @mail_to: vermadivakar2022@gmail.com
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleResponse {
    private int id;
    private String name;
}
