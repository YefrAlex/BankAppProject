package de.telran_yefralex.BankAppProject.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        String username=claims.getSubject();
        String email=(String) claims.get("email");
        String role=(String) claims.get("roles");
        return new JwtAuthentication(username, email, Arrays.asList(role));
    }
}
