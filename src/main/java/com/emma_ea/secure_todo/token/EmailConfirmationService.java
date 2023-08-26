package com.emma_ea.secure_todo.token;

import com.emma_ea.secure_todo.model.UserAuthDetail;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmailConfirmationService {

    private final EmailConfirmationRepository repository;

    public EmailConfirmationService(EmailConfirmationRepository repository) {
        this.repository = repository;
    }

    public void saveConfirmationToken(EmailConfirmationToken token) {
        repository.save(token);
    }

    public UserAuthDetail confirmEmail(String token) throws Exception {
        // find email obj
        // optional: check for expiry
        // get user
        // return user
       EmailConfirmationToken emailToken =  repository.findByToken(token).get();
       if (emailToken.getConfirmedAt() != null) {
           throw new Exception("Email already confirmed");
       }
       if (Duration.between(emailToken.getExpiresAt(), LocalDateTime.now()).isNegative()) {
           throw new Exception("Email already expired");
       }
       emailToken.setConfirmedAt(LocalDateTime.now());
       repository.save(emailToken);
       return repository.findById(emailToken.getId()).get().getUser();
    }
}
