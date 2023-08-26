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
public class TodoController {

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
