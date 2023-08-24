package com.emma_ea.secure_todo.controller;

import com.emma_ea.secure_todo.model.TodoItem;
import com.emma_ea.secure_todo.model.http.UserAuthEntity;
import com.emma_ea.secure_todo.model.http.UserAuthRequest;
import com.emma_ea.secure_todo.model.http.UserAuthResponse;
import com.emma_ea.secure_todo.service.UserSignInSignUpService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class TodoController {

    private UserSignInSignUpService service;

    @PostMapping("/auth/register")
    public ResponseEntity<UserAuthEntity<UserAuthResponse>> signUp(@RequestBody UserAuthRequest user) {
        return service.register(user);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<UserAuthEntity<UserAuthResponse>> signIn(@RequestBody UserAuthRequest user) {
        return service.login(user);
    }

    @GetMapping("/todos/all")
    public List<String> allTodos() {
        return Collections.emptyList();
    }

    @PostMapping("/todos/save")
    public String saveTodo(@RequestBody TodoItem todo) {
        return "saved";
    }

    @PutMapping("/todos/update")
    public String updateTodo(@RequestBody TodoItem todo) {
        return "updated";
    }

    @DeleteMapping("/todos/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
       return "deleted: " + id;
    }

}
