package br.com.ramir.users.service;

import br.com.ramir.users.exceptions.MessageNotSentException;
import br.com.ramir.users.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("my.gmail@gmail.com");
        mailSender.setPassword("password");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    public Boolean sendMessage(Message msg) throws MessageNotSentException {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(msg.getRecipients());
            message.setSubject("SYSTEM MESSAGE");
            message.setText(msg.getMessage());
            emailSender.send(message);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
