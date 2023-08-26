package com.emma_ea.secure_todo.mail_service;

public interface EmailSender {
    public void sendTo(String to, String subject, String message);
}
