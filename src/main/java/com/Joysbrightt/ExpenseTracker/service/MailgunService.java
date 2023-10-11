package com.Joysbrightt.ExpenseTracker.service;

public interface MailgunService {


    void sendEmail(String to, String subject, String text);


}
