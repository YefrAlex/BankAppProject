package de.telran_yefralex.BankAppProject.mail;

import de.telran_yefralex.BankAppProject.entity.Account;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailScheduler {

    private final EmailService emailService;

    public EmailScheduler(EmailService emailService) {
        this.emailService=emailService;
    }


    @Scheduled(cron = "0 0 12 * * MON") // Каждый понедельник в 12:00 PM
    public void sendWeeklyEmails() {
        emailService.sendAccountsBalance();
    }
}
