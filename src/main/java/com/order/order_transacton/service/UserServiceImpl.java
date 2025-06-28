package com.order.order_transacton.service;

import com.order.order_transacton.dto.request.LoginRequest;
import com.order.order_transacton.dto.request.UserRequest;
import com.order.order_transacton.entities.Credential;
import com.order.order_transacton.entities.User;
import com.order.order_transacton.entities.enums.Role;
import com.order.order_transacton.repository.UserRepository;
import com.order.order_transacton.security.JwtTokenService;
import com.order.order_transacton.service.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenService tokenService;

    @Override
    public User register(UserRequest userRequest) {
        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setGender(userRequest.getGender());
        user.setPhoneNum(userRequest.getPhoneNum());
        user.setUserRole(Role.getByrole(userRequest.getRole()));

        Credential credential = new Credential();
        credential.setEmail(user.getEmail());
        credential.setRole(user.getUserRole());
        credential.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setCredential(credential);
        credential.setCredentialsNonExpired(Boolean.TRUE);
        credential.setAccountNonExpired(Boolean.TRUE);
        credential.setAccountNonLocked(Boolean.TRUE);
        credential.setEnabled(Boolean.TRUE);
        return userRepository.save(user);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                loginRequest.getPassword(),
                List.of(new SimpleGrantedAuthority(Role.getByrole(loginRequest.getRole()).getRole()))));
        if (authenticate.isAuthenticated()) return tokenService.getToken(loginRequest);
        throw new RuntimeException("Invalid credentials.");
    }

    @Override
    public List<User> getAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        log.info("Login in user :: {}", name);
        return userRepository.findAll();
    }
}
