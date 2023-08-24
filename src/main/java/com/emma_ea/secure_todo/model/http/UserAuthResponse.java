package com.emma_ea.secure_todo.model.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class UserAuthResponse {
    private String token;

    public UserAuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
