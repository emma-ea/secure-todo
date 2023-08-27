package com.emma_ea.secure_todo.config;

import com.emma_ea.secure_todo.model.http.UserAuthEntity;
import com.emma_ea.secure_todo.model.http.UserAuthResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import static com.emma_ea.secure_todo.constants.TodoRequestStatus.APP_ERROR;

@ControllerAdvice
public class TodoExceptionsConfig  {

    @ExceptionHandler
    public ResponseEntity<UserAuthEntity<UserAuthResponse>> exceptionsHandler(Exception e) {
        UserAuthEntity<UserAuthResponse> uae = new UserAuthEntity<>(
               APP_ERROR,
               e.getMessage(),
                null
        );
       return new ResponseEntity<>(uae, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
