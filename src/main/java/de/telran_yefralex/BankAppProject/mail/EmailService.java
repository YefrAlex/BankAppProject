package de.telran_yefralex.BankAppProject.mail;

import de.telran_yefralex.BankAppProject.entity.Account;
import de.telran_yefralex.BankAppProject.repository.AccountRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private AccountRepository accountRepository;

    public EmailService(JavaMailSender javaMailSender, AccountRepository accountRepository) {
        this.javaMailSender = javaMailSender;
        this.accountRepository = accountRepository;
    }

    public void sendAccountsBalance() {
        List<Account> accounts = accountRepository.findAll();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = currentDate.format(formatter);

        for (Account account : accounts) {
            String to = account.getClientId().getEmail();
            String subject = "your account " + account.getAccountNumber() + " balance as of " + formattedDate;
            String body = "Dear Mr/Mrs " + account.getClientId().getFirstName() +" " + account.getClientId().getLastName() +
                    " the balance on your account is equal " + account.getBalance() +" " + account.getCurrencyCode().getCurrencyName();
            sendEmail(to, subject, body);
        }
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }
}
