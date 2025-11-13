package com.example.objectMapper.security;

import com.example.objectMapper.persistence.model.UserEntity;
import com.example.objectMapper.persistence.repository.UserRepository;
import com.example.objectMapper.security.dto.ProfilerUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OurUserDetailedService implements UserDetailsService {

    private final UserRepository userRepo;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByEmail(email);

        if (userEntity == null)
            throw new UsernameNotFoundException(String.format("User's email '%s' not found", email));

        log.info("Load user {} by email: {}", userEntity, email);

        List<SimpleGrantedAuthority> userAuthorities = List.of(new SimpleGrantedAuthority(
                userEntity.getRole().name()));
        return new ProfilerUserDetails(
                userEntity.getUuid(), userEntity., userEntity.getPassword(), userAuthorities);
    }
}
