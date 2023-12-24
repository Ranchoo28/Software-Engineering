package it.unical.demacs.FundasticServer.Service;

import it.unical.demacs.FundasticServer.Handler.EmailHandler;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EmailService {
    private final EmailHandler emailHandler = EmailHandler.getInstance();

    public void emailServiceSendWelcomeEmail(String toEmail, String subject, String body) {
        ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
        emailExecutor.execute(() -> emailHandler.sendEmail(toEmail, subject, body));
        emailExecutor.shutdown();
    }
}