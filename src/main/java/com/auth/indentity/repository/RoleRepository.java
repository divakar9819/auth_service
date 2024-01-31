package com.auth.indentity.repository;

import com.auth.indentity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Divakar Verma
 * @created_at : 31/01/2024 - 3:45 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
