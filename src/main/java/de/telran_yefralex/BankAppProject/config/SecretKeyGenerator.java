package de.telran_yefralex.BankAppProject.config;

import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyGenerator {

    public static void main(String[] args) {
        byte[] secretKeyBytes = generateRandomBytes(64);
        String secretKey = Base64.getEncoder().encodeToString(secretKeyBytes);
        System.out.println("Your secret key: " + secretKey);
    }

    private static byte[] generateRandomBytes(int length) {
        byte[] bytes = new byte[length];
        new SecureRandom().nextBytes(bytes);
        return bytes;
    }
}
