package de.telran_yefralex.BankAppProject.security.service;

import de.telran_yefralex.BankAppProject.entity.Client;
import de.telran_yefralex.BankAppProject.entity.Employee;
import de.telran_yefralex.BankAppProject.repository.ClientRepository;
import de.telran_yefralex.BankAppProject.repository.EmployeeRepository;
import de.telran_yefralex.BankAppProject.security.jwt.JwtAuthentication;
import de.telran_yefralex.BankAppProject.security.jwt.JwtProvider;
import de.telran_yefralex.BankAppProject.security.jwt.JwtRequest;
import de.telran_yefralex.BankAppProject.security.jwt.JwtResponse;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final Map<String, String> refreshStorage=new HashMap<>();
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthService(ClientRepository clientRepository, EmployeeRepository employeeRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.clientRepository=clientRepository;
        this.employeeRepository=employeeRepository;
        this.jwtProvider=jwtProvider;
        this.passwordEncoder=passwordEncoder;
    }

    public JwtResponse login(JwtRequest authRequest) throws AuthException {
        // Получаем данные из запроса
        String username=authRequest.getUsername();
        String password=authRequest.getPassword();
        // Определяем сущность на основе email
        if (username.contains("@bank.com")) {
            // Employee
            return authenticateEmployee(username, password);
        } else {
            // Client
            return authenticateClient(username, password);
        }
    }

    private JwtResponse authenticateEmployee(String email, String password) throws AuthException {
        Employee employee=employeeRepository.findEmployeeByEmail(email);
        if (employee != null && passwordEncoder.matches(password, employee.getPassword())) {
            // Генерация JWT для Employee
            String jwt=jwtProvider.generateAccessToken(employee);
            String refreshToken=jwtProvider.generateRefreshToken(employee);
            refreshStorage.put(employee.getEmail(), refreshToken);
            return new JwtResponse(jwt, refreshToken);
        } else {
            throw new AuthException("Invalid credentials for Employee");
        }
    }

    private JwtResponse authenticateClient(String email, String password) throws AuthException {
        Client client=clientRepository.findClientByEmail(email);
        if (client != null && passwordEncoder.matches(password, client.getPassword())) {
            // Генерация JWT для Client
            String accessToken=jwtProvider.generateAccessToken(client);
            String refreshToken=jwtProvider.generateRefreshToken(client);
            refreshStorage.put(client.getEmail(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Invalid credentials for Client");
        }
    }

    public JwtResponse getAccessToken(String refreshToken) throws AuthException {
        // Validate the provided refresh token
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            // Extract claims from the refresh token
            final Claims claims=jwtProvider.getRefreshClaims(refreshToken);
            // Get the user login from the token claims
            final String login=claims.getSubject();
            // Retrieve the stored refresh token for the user
            final String savedRefreshToken=refreshStorage.get(login);
            // Compare the stored refresh token with the provided token
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken) && login.contains("@bank.com")) {
                // Fetch the user data
                final Employee employee=employeeRepository.findEmployeeByEmail(login);
                if (employee == null) {
                    throw new AuthException("User is not found");
                }
                // Generate a new access token
                final String accessToken=jwtProvider.generateAccessToken(employee);
                // Return a JwtResponse with the new access token
                return new JwtResponse(accessToken, null);
            }
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken) && !login.contains("@bank.com")) {
                // Fetch the user data
                final Client client=clientRepository.findClientByEmail(login);
                if (client == null) {
                    throw new AuthException("User is not found");
                }
                // Generate a new access token
                final String accessToken=jwtProvider.generateAccessToken(client);
                // Return a JwtResponse with the new access token
                return new JwtResponse(accessToken, null);
            }

        }
        // Return a JwtResponse with null values if validation fails
        return new JwtResponse(null, null);
    }

    public JwtResponse refresh(String refreshToken) throws AuthException {
        // Validate the provided refresh token
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            // Extract claims from the refresh token
            final Claims claims=jwtProvider.getRefreshClaims(refreshToken);
            // Get the user login from the token claims
            final String login=claims.getSubject();
            // Retrieve the stored refresh token for the user
            final String savedRefreshToken=refreshStorage.get(login);
            // Compare the stored refresh token with the provided token
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken) && login.contains("@bank.com")) {
                // Fetch the user data
                final Employee employee=employeeRepository.findEmployeeByEmail(login);
                if (employee == null) {
                    throw new AuthException("User is not found");
                }
                // Generate new access and refresh tokens
                final String accessToken=jwtProvider.generateAccessToken(employee);
                final String newRefreshToken=jwtProvider.generateRefreshToken(employee);
                // Update the stored refresh token for the user
                refreshStorage.put(employee.getEmail(), newRefreshToken);
                // Return a JwtResponse with the new access and refresh tokens
                return new JwtResponse(accessToken, newRefreshToken);
            }
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken) && !login.contains("@bank.com")) {
                // Fetch the user data
                final Client client=clientRepository.findClientByEmail(login);
                if (client == null) {
                    throw new AuthException("User is not found");
                }
                // Generate new access and refresh tokens
                final String accessToken=jwtProvider.generateAccessToken(client);
                final String newRefreshToken=jwtProvider.generateRefreshToken(client);
                // Update the stored refresh token for the user
                refreshStorage.put(client.getEmail(), newRefreshToken);
                // Return a JwtResponse with the new access and refresh tokens
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        // Throw an AuthException if validation fails
        throw new AuthException("Invalid JWT token");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
