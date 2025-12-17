package com.example.springDataJdbc.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class OidcLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        setDefaultTargetUrl("/");
        super.onLogoutSuccess(request, response, authentication);
    }

    private void revokeGithubToken(String token) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(System.getenv("GITHUB_CLIENT_ID"),
                System.getenv("GITHUB_CLIENT_SECRET"));
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = "{\"access_token\":\"" + token + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        try {
            rt.postForObject("https://api.github.com/applications/{clientId}/token",
                    entity, String.class, System.getenv("GITHUB_CLIENT_ID"));
        } catch (Exception ignore) { /* не критично */ }
    }
}