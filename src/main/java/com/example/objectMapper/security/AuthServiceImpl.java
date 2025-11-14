package com.example.objectMapper.security;

import com.example.objectMapper.persistence.model.UserEntity;
import com.example.objectMapper.persistence.repository.UserRepository;
import com.example.objectMapper.security.dto.AuthenticatedUserResponseDto;
import com.example.objectMapper.security.dto.LoginRequestDto;
import com.example.objectMapper.security.token.JWTUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;
    private final UserRepository userRepository;
    private final OurUserDetailedService userDetailsService;
    private final static long LOCK_DURATION = 30 * 60 * 1000;

    @Override
    public AuthenticatedUserResponseDto login(LoginRequestDto loginRequestDto) {
        UserEntity user = userRepository.findByEmail(loginRequestDto.username());

        if (user != null && user.isAccountLocked()) {
            if (isLockTimeExpired(user)) {
                user.resetFailedAttempt();
                userRepository.save(user);
            } else {
                log.warn("Attempt to login to locked account: {}", loginRequestDto.username());
                throw new RuntimeException("Account is locked. Try again later");
            }
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.username(),
                            loginRequestDto.password()
                    )
            );

            if (user != null) {
                user.resetFailedAttempt();
                userRepository.save(user);
            }

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtils.generateToken(userDetails);

            log.info("Successful login for user: {}", loginRequestDto.username());
            return new AuthenticatedUserResponseDto(userDetails.getUsername(), token);

        } catch (BadCredentialsException e) {
            if (user != null) {
                user.incrementFailedAttempt();
                if (user.getFailedAttempt() >= 5) {
                    user.lockAccount();
                    log.warn("Account locked due to 5 failed attempts: {}", loginRequestDto.username());
                }
                userRepository.save(user);
            }

            log.warn("Failed login attempt for user: {}", loginRequestDto.username());

            throw new RuntimeException("Invalid username or password");
        } catch (LockedException e) {
            log.warn("Attempt to login to locked account: {}", loginRequestDto.username());
            throw new RuntimeException("Account is locked");
        }
    }

    private boolean isLockTimeExpired(UserEntity user) {
        if (user.getLockTime() == null) return false;

        long lockTime = user.getLockTime().getTime();
        long currentTime = System.currentTimeMillis();

        return (currentTime - lockTime) > LOCK_DURATION;
    }

    @Override
    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (authentication != null) ? authentication.getName() : null;
    }
}