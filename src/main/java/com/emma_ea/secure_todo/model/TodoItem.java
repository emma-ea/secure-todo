package com.emma_ea.secure_todo.model;


import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class TodoItem {
    private Long id;
    private String title;
    private String task;
}
