package com.example.objectMapper.security.filter;

import com.example.objectMapper.security.token.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  
    private final JWTUtils jwtUtils;
  
    private OurUserDetailedService ourUserDetailedService;  
  
    // Метод, выполняемый для каждого HTTP запроса  
    @Override  
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
  
        // Шаг 1: Извлечение заголовка авторизации из запроса  

        // Шаг 2: Проверка наличия заголовка авторизации  
        
        // Шаг 3: Извлечение токена из заголовка  
  
        // Шаг 4: Извлечение имени пользователя из JWT токена
                
        // Шаг 5: Проверка валидности токена и аутентификации  
     
        // Шаг 6: Создание нового контекста безопасности  
      
        // Шаг 7: Передача запроса на дальнейшую обработку в фильтрующий цепочке  
    }  
  
}