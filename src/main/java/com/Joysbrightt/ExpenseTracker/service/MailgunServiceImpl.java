package com.Joysbrightt.ExpenseTracker.service;

import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;
import net.sargue.mailgun.Mail;
import org.apache.tomcat.util.descriptor.web.MessageDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.text.MessageFormat;

public class MailgunServiceImpl implements MailgunService{

private final String apiKey = "1b226bb55c3d413d34c51633e3402aec-db137ccd-7b72c401";
private final String domain = "https://app.mailgun.com/app/sending/domains/sandboxca7cddbb722c4790a272e181f9b4f180.mailgun.org";

@Autowired
private JavaMailSender javaMailSender;
    @Override
    public void sendEmail(String to, String subject, String text, String body) {

//        MailgunClient mailgunClient = new Mail

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("oluwatomisinoladoyin@gmail.com");
        message.setTo(to);

        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);

    }
}
