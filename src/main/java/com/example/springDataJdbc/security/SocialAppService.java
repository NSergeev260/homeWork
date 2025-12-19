package com.example.springDataJdbc.security;

import com.example.springDataJdbc.persistence.model.UserData;
import com.example.springDataJdbc.persistence.model.UserRole;
import com.example.springDataJdbc.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SocialAppService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(SocialAppService.class);

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String login = oAuth2User.getAttribute("login");
        String providerId = oAuth2User.getAttribute("id").toString();

        Optional<UserData> optionalUser = userRepository.findUserByEmail(email);
        UserData userData;

        if (optionalUser.isEmpty()) {
            userData = new UserData();
            userData.setEmail(email);
            userData.setName(login);
            userData.setProvider("github");
            userData.setProviderId(providerId);
            userData.setRole(UserRole.USER);
            userData = userRepository.insertUser(userData);
            logger.info("New user saved. email: {}", email);
        } else {
            userData = optionalUser.get();
        }

        List<SimpleGrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority("ROLE_" + userData.getRole()));

        return new DefaultOAuth2User(authorities,
                oAuth2User.getAttributes(),
                "login");
    }
}

