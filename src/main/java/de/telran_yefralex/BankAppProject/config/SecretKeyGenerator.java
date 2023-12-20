package de.telran_yefralex.BankAppProject.config;

import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyGenerator {

    public static void main(String[] args) {
        // Генерация случайного секретного ключа
        byte[] secretKeyBytes = generateRandomBytes(64);

        // Кодирование ключа в Base64
        String secretKey = Base64.getEncoder().encodeToString(secretKeyBytes);

        // Вывод секретного ключа
        System.out.println("Your secret key: " + secretKey);
    }

    private static byte[] generateRandomBytes(int length) {
        byte[] bytes = new byte[length];
        new SecureRandom().nextBytes(bytes);
        return bytes;
    }
}
