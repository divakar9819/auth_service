package com.auth.indentity.config.bean;
import com.auth.indentity.exception.security.CustomAuthenticationEntryPoint;
import com.auth.indentity.serviceImpl.security.CustomUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Divakar Verma
 * @created_at : 08/01/2024 - 6:34 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
@Configuration
public class ConfigBean {


    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return  new CustomAuthenticationEntryPoint();
    }

}
