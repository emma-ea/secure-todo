package com.emma_ea.secure_todo.service;

import com.emma_ea.secure_todo.model.http.UserAuthEntity;
import com.emma_ea.secure_todo.model.http.UserAuthRequest;
import com.emma_ea.secure_todo.model.UserAuthDetail;
import com.emma_ea.secure_todo.model.UserRole;
import com.emma_ea.secure_todo.model.http.UserAuthResponse;
import com.emma_ea.secure_todo.token.EmailConfirmationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserSignInSignUpService {

    private UserAuthService userAuthService;


    public ResponseEntity<UserAuthEntity<UserAuthResponse>> register(UserAuthRequest user) {
        UserAuthDetail userAuthDetail = new UserAuthDetail(
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                UserRole.USER
        );
       return userAuthService.signUpUser(userAuthDetail);
    }

    public ResponseEntity<UserAuthEntity<UserAuthResponse>> login(UserAuthRequest user) {
       return userAuthService.signInUser(user);
    }

    public String confirmEmail(String token) {
       return userAuthService.confirmEmail(token);
    }
}
