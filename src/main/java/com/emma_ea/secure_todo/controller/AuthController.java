package com.emma_ea.secure_todo.controller;

import com.emma_ea.secure_todo.model.http.UserAuthEntity;
import com.emma_ea.secure_todo.model.http.UserAuthRequest;
import com.emma_ea.secure_todo.model.http.UserAuthResponse;
import com.emma_ea.secure_todo.service.UserSignInSignUpService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private UserSignInSignUpService service;

    @PostMapping("/register")
    public ResponseEntity<UserAuthEntity<UserAuthResponse>> signUp(@RequestBody UserAuthRequest user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthEntity<UserAuthResponse>> signIn(@RequestBody UserAuthRequest user) {
        return service.login(user);
    }

    @GetMapping("/confirm")
    public String confirmEmail(@RequestParam String token) {
        return service.confirmEmail(token);
    }

}
