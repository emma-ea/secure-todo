package com.emma_ea.secure_todo.model.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RequestEntityResponse {

    public static ResponseEntity<UserAuthEntity<UserAuthResponse>> buildResponse(
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
