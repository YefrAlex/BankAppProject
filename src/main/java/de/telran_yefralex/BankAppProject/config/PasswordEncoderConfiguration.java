package de.telran_yefralex.BankAppProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderConfiguration {

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}

//    public static void main(String[] args) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
//        System.out.println(encoder.encode("user"));
//    }
//  qwe ==   $2a$04$U.pF7LPivIFtjozAIrMk/eoWS9BKbX6Nr7KMN/xYOQ5sKfCZrNO3a
// user ==