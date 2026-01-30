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
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticatedUserResponseDto login(LoginRequestDto loginRequestDto) {
        System.out.println("=== DETAILED AUTH DEBUG ===");
        System.out.println("Login attempt for: " + loginRequestDto.username());

        try {
            UserEntity user = userRepository.findByEmail(loginRequestDto.username());
            System.out.println("User found in DB: " + (user != null));

            if (user != null) {
                System.out.println("User details:");
                System.out.println("  - Email: " + user.getEmail());
                System.out.println("  - Password in DB: " + user.getPassword());
                System.out.println("  - Role: " + user.getRole());

                boolean passwordMatches = passwordEncoder.matches(loginRequestDto.password(), user.getPassword());
                System.out.println("Manual password check: " + passwordMatches);

                if (!passwordMatches) {
                    System.out.println("PASSWORD MISMATCH!");
                    System.out.println("Input password: " + loginRequestDto.password());
                    System.out.println("DB password: " + user.getPassword());
                }
            } else {
                System.out.println("USER NOT FOUND IN DATABASE!");
            }

            System.out.println("Attempting Spring Security authentication...");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.username(),
                            loginRequestDto.password()
                    )
            );

            System.out.println("AUTHENTICATION SUCCESSFUL!");
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtils.generateToken(userDetails);

            return new AuthenticatedUserResponseDto(userDetails.getUsername(), token);

        } catch (BadCredentialsException e) {
            System.out.println("BAD CREDENTIALS EXCEPTION: Username or password is incorrect");
            throw new RuntimeException("Invalid username or password");
        } catch (Exception e) {
            System.out.println("OTHER EXCEPTION: " + e.getClass().getSimpleName());
            System.out.println("Exception message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Authentication failed: " + e.getMessage());
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