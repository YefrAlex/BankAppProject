package de.telran_yefralex.BankAppProject.security.jwt;

import de.telran_yefralex.BankAppProject.entity.enums.Role;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class JwtUtils {

    /**
     * Generates a {@link JwtAuthentication} object from JWT claims.
     * <p>
     * This method extracts the username and roles from the provided JWT claims,
     * and creates a {@link JwtAuthentication} object with this information.
     * </p>
     *
     * @param claims the JWT claims.
     * @return a JwtAuthentication object containing the username and roles.
     */
    public static JwtAuthentication generate(Claims claims) {

        String username = claims.getSubject();
        String email =(String)claims.get("email");
        String role =(String)claims.get("roles");

        return new JwtAuthentication(username, email, Arrays.asList(role));
    }
}
