package com.order.order_transacton.security;

import com.order.order_transacton.entities.Credential;
import com.order.order_transacton.repository.CredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final CredentialRepository credentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credential credential = credentialRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Invalid email.."));
        return new CustomUserDetails(credential.getEmail(),
                credential.getPassword(),
                credential.getRole(),
                credential.isAccountNonExpired(),
                credential.isAccountNonLocked(),
                credential.isCredentialsNonExpired(),
                credential.isEnabled());
    }
}
