package com.Joysbrightt.ExpenseTracker.controller;

import com.Joysbrightt.ExpenseTracker.dtos.CreateUserRequest;
import com.Joysbrightt.ExpenseTracker.dtos.LoginUser;
import com.Joysbrightt.ExpenseTracker.model.User;
import com.Joysbrightt.ExpenseTracker.service.MailgunService;
import com.Joysbrightt.ExpenseTracker.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    private MailgunService mailgunService;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/send-email-template")
    private void sendEmailTemplate(String to, String subject, String body) throws MessagingException {
        //Create a MimeMessage object.
        SimpleMailMessage message = new SimpleMailMessage();

        // Set the sender and recipicient email addresses.

        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);

    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody CreateUserRequest request){
        User user = userService.createUser(request);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody LoginUser loginUser){
        User user = userService.loginUser(loginUser);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }
}
