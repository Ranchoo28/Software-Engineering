package it.unical.demacs.FundasticServer.Handler;

import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.parameters.P;

import java.util.Properties;


public class EmailHandler {
    private EmailHandler(){}
    private static EmailHandler instance;
    public static EmailHandler getInstance(){
        if (instance == null) return new EmailHandler();
        else return instance;
    }
    @Value("${email.password}") String password;

    public void sendEmail(String toEmail, String subject, String body) {
        try{
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            Properties properties = new Properties();

            mailSender.setHost("smtp.gmail.com");
            mailSender.setUsername("projectuid28@gmail.com");
            mailSender.setPassword("mhsujioysswltzpj");
            mailSender.setPort(587);
            properties.setProperty("mail.smtp.auth", "true");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            mailSender.setJavaMailProperties(properties);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("projectuid28@gmail.com");
            message.setTo(toEmail);
            message.setText(body);
            message.setSubject(subject);
            mailSender.send(message);
        }catch(Exception ignored){}
    }
}
