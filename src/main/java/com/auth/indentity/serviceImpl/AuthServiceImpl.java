package com.auth.indentity.serviceImpl;
import com.auth.indentity.entity.Role;
import com.auth.indentity.entity.User;
import com.auth.indentity.exception.UserNotFoundException;
import com.auth.indentity.payload.request.UserLoginRequest;
import com.auth.indentity.payload.request.UserRegisterRequest;
import com.auth.indentity.payload.response.RoleResponse;
import com.auth.indentity.payload.response.TokenResponse;
import com.auth.indentity.payload.response.UserRegisterResponse;
import com.auth.indentity.repository.UserRepository;
import com.auth.indentity.service.AuthService;
import com.auth.indentity.utils.security.AccessToken;
import com.auth.indentity.utils.security.ITokenProvider;
import com.auth.indentity.utils.security.jwt.IJwtTokenHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Divakar Verma
 * @created_at : 10/01/2024 - 6:38 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ITokenProvider tokenProvider;

    @Autowired
    private IJwtTokenHelper tokenHelper;
    @Override
    public UserRegisterResponse userRegistration(UserRegisterRequest userRegisterRequest) {
        userRegisterRequest.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        User user = userRequestToUser(userRegisterRequest);
        User createdUser = userRepository.save(user);
        return userToUserResponse(createdUser);
    }

    @Override
    public AccessToken userLogin(UserLoginRequest userLoginRequest) {
        String username = userLoginRequest.getUsername();
        String password = userLoginRequest.getPassword();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        if(authentication.isAuthenticated()){
            Set<Role> roles = userRepository.findByUsername(username).getRoles();
            return tokenProvider.createToken(username,roles);
        }
        else {
            throw new UserNotFoundException("Invalid user credentials");
        }

    }

    @Override
    public List<UserRegisterResponse> getAllUser() {
        List<User> users = userRepository.findAll();
        System.out.println(users);
        List<UserRegisterResponse> userRegisterResponses = users.stream().map(this::userToUserResponse).toList();
        return userRegisterResponses;
    }

    @Override
    public Mono<TokenResponse> validateToken(String token) {
        System.out.println(token);
        AccessToken accessToken = new AccessToken(token);
        boolean isTokenValid = tokenProvider.validateToken(accessToken);
        TokenResponse tokenResponse = new TokenResponse();
        if(isTokenValid){
            tokenResponse.setMessage("Valid token");
            tokenResponse.setSuccess(true);
            tokenResponse.setUsername(tokenProvider.getUsernameFromToken(token));
        }
        else {
            tokenResponse.setMessage("Invalid token");
            tokenResponse.setSuccess(false);
        }
        return Mono.just(tokenResponse);
    }

    public User userRequestToUser(UserRegisterRequest userRegisterRequest){
        return this.modelMapper.map(userRegisterRequest,User.class);
    }


    public RoleResponse roleToRoleResponse(Role role){
        return this.modelMapper.map(role,RoleResponse.class);
    }

    public UserRegisterResponse userToUserResponse(User user){
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setId(user.getId());
        userRegisterResponse.setUsername(user.getUsername());
        userRegisterResponse.setEmail(user.getEmail());
        Set<Role> roles = user.getRoles();
        Set<RoleResponse> roleResponses = roles.stream().map(this::roleToRoleResponse).collect(Collectors.toSet());
        userRegisterResponse.setRoleResponses(roleResponses);
        return userRegisterResponse;
    }
}
