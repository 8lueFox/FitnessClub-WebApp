package com.kacper.fitnessclub.services;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String content);
}
