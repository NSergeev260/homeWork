package com.example.objectMapper.security.token;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
public class JWTUtils {  
  
    // Ключ шифрования для JWT  
    private SecretKey secretKey;
  
    // Время действия токена в миллисекундах (24 часа)  
    private static final long EXPIRATION_TIME;
  
    public JWTUtils(){  
        // Строка, используемая для создания секретного ключа  
        String secreteString = //
        byte[] keyBytes = //
    }  
    /*Метод для генерации JWT токена на основе данных пользователя*/  
    public String generateToken(UserDetails userDetails){
        
    }  
  
    // Метод для генерации токена обновления (refresh token) с дополнительными данными  
    public String generateRefreshToken(HashMap<String, Object> claims, UserDetails userDetails){
    }  
  
    public String extractUsername(String token) {  
        return extractClaims(token, Claims::getSubject);  
    }  
  
    // Метод для извлечения имени пользователя из токена  
    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction {
    }  
  
    public boolean isTokenValid(String token, UserDetails userDetails) {

    }
  
    private boolean isTokenExpired(String token) {  
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }  
}