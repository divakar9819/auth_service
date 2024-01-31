package com.auth.indentity.serviceImpl.security;

import com.auth.indentity.entity.Role;
import com.auth.indentity.entity.User;
import com.auth.indentity.repository.RoleRepository;
import com.auth.indentity.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Divakar Verma
 * @created_at : 11/01/2024 - 11:29 am
 * @mail_to: vermadivakar2022@gmail.com
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository repository;
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException(username);
        }
        Set<Role> roles = user.getRoles();

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(getGrantedAuthority(roles))
                .accountExpired(false)
                .accountLocked(false)
                .disabled(false)
                .credentialsExpired(false)
                .build();
    }

    public Set<GrantedAuthority> getGrantedAuthority(Set<Role> roles){
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role: roles){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return grantedAuthorities;

    }
}