package com.emma_ea.secure_todo.service;

import com.emma_ea.secure_todo.constants.TodoRequestStatus;
import com.emma_ea.secure_todo.model.http.UserAuthEntity;
import com.emma_ea.secure_todo.model.http.UserAuthRequest;
import com.emma_ea.secure_todo.model.UserAuthDetail;
import com.emma_ea.secure_todo.model.http.UserAuthResponse;
import com.emma_ea.secure_todo.repository.UserAuthRepository;
import com.emma_ea.secure_todo.token.EmailConfirmationService;
import com.emma_ea.secure_todo.token.EmailConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.emma_ea.secure_todo.constants.TodoReqStrings.*;
import static com.emma_ea.secure_todo.constants.TodoRequestStatus.*;
import static com.emma_ea.secure_todo.model.http.RequestEntityResponse.buildResponse;

@Service
@AllArgsConstructor
public class UserAuthService implements UserDetailsService {
    private  UserAuthRepository repository;
    private EmailConfirmationService emailCTService;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserAuthDetail loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(USERNAME_NOT_FOUND));
    }

    public ResponseEntity<UserAuthEntity<UserAuthResponse>> signUpUser(UserAuthDetail user) {
        boolean userExist = repository.findByEmail(user.getEmail()).isPresent();
        if (userExist) {
            return buildResponse(SIGNUP_ERROR, HttpStatus.CONFLICT, EMAIL_ALREADY_EXISTS, null);
        }
        String passwd = passwordEncoder.encode(user.getPassword().trim());
        user.setPassword(passwd);
        repository.save(user);

        String token = UUID.randomUUID().toString();
        EmailConfirmationToken emailCT = new EmailConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                null,
                user
        );
        emailCTService.saveConfirmationToken(emailCT);

        // TODO: Send Email

        return buildResponse(SUCCESS, HttpStatus.CREATED, EMAIL_NEED_VERIFICATION(user.getUsername()), "access-token");
    }

    public ResponseEntity<UserAuthEntity<UserAuthResponse>> signInUser(UserAuthRequest user) {
        UserAuthDetail authUser = loadUserByUsername(user.getEmail());
        if (!authUser.isEnabled()) {
            return buildResponse(SIGIN_ERROR, HttpStatus.FORBIDDEN, SIGN_IN_FAILED_VERIFY_EMAIL, null);
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authUser.getEmail(),
                authUser.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (!authentication.isAuthenticated()) {
            return buildResponse(SIGIN_ERROR, HttpStatus.CONFLICT, SIGN_IN_FAILED, null);
        }

        return buildResponse(SUCCESS, HttpStatus.OK, SIGN_IN_SUCCESS, "refresh-token");
    }

}

