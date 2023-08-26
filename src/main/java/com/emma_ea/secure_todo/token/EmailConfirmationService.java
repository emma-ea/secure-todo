package com.emma_ea.secure_todo.token;

import org.springframework.stereotype.Service;

@Service
public class EmailConfirmationService {

    private final EmailConfirmationRepository repository;


    public EmailConfirmationService(EmailConfirmationRepository repository) {
        this.repository = repository;
    }

    public void saveConfirmationToken(EmailConfirmationToken token) {
        repository.save(token);
    }
}
