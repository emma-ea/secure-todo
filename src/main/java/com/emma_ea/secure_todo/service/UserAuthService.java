package com.emma_ea.secure_todo.service;

import com.emma_ea.secure_todo.config.TodoRequestStatus;
import com.emma_ea.secure_todo.model.http.UserAuthEntity;
import com.emma_ea.secure_todo.model.http.UserAuthRequest;
import com.emma_ea.secure_todo.model.UserAuthDetail;
import com.emma_ea.secure_todo.model.http.UserAuthResponse;
import com.emma_ea.secure_todo.repository.UserAuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAuthService implements UserDetailsService {
    private  UserAuthRepository repository;
    @Override
    public UserAuthDetail loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User with email provided does not exist."));
    }

    public ResponseEntity<UserAuthEntity<UserAuthResponse>> signUpUser(UserAuthDetail user) {
        boolean userExist = repository.findByEmail(user.getEmail()).isPresent();
        if (userExist) {
            String msg = "Email already exists";
            return buildResponse(TodoRequestStatus.SIGNUP_ERROR, HttpStatus.CONFLICT, msg, null);
        }
        String passwd = passwordEncoder().encode(user.getPassword().trim());
        user.setPassword(passwd);
        repository.save(user);
        // TODO: confirmation token

        String msg = "Registration successful "+ user.getUsername() + ", Please verify email";
        return buildResponse(TodoRequestStatus.SUCCESS, HttpStatus.CREATED, msg, "access-token");
    }

    public ResponseEntity<UserAuthEntity<UserAuthResponse>> signInUser(UserAuthRequest user) {
        UserAuthDetail authUser = loadUserByUsername(user.getEmail());
        String passwd = passwordEncoder().encode(user.getPassword().trim());
        if (passwd.compareTo(authUser.getPassword()) != 0) {
            String msg = "Invalid email or password";
            return buildResponse(TodoRequestStatus.SIGIN_ERROR, HttpStatus.CONFLICT, msg, null);
        }
        String msg = "Authentication successful";
        return buildResponse(TodoRequestStatus.SUCCESS, HttpStatus.OK, msg, "refresh-token");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private ResponseEntity<UserAuthEntity<UserAuthResponse>> buildResponse(
            int reqStatus,
            HttpStatus httpStatus,
            String message,
            String token
    ) {
        UserAuthEntity<UserAuthResponse> res = new UserAuthEntity<>(
                reqStatus,
                message,
                new UserAuthResponse(token)
        );
        return new ResponseEntity<>(res, httpStatus);
    }
}

