package com.Joysbrightt.ExpenseTracker.controller;


import com.Joysbrightt.ExpenseTracker.service.MailgunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MailgunServiceController {

    @Autowired
    private MailgunService mailgunService;

    @GetMapping("/send-email")
    public void sendEmail(){
        mailgunService.sendEmail("oluwatomisinoladoyin@gmail.com",
                "trying the email service out",
                "This is the email service tested using mailgun service api to run");
    }
}
