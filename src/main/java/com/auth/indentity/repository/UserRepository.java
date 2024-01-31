package com.auth.indentity.repository;

import com.auth.indentity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Divakar Verma
 * @created_at : 31/01/2024 - 3:22 pm
 * @mail_to: vermadivakar2022@gmail.com
 */

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsername(String username);
}
