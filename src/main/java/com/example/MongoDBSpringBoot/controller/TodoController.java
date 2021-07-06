package com.example.MongoDBSpringBoot.controller;

import com.example.MongoDBSpringBoot.model.TodoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.MongoDBSpringBoot.repository.TodoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping(value="/todos")
    public ResponseEntity<?> getAllTodos(){
        List<TodoDTO> todos = todoRepository.findAll();
        if(todos.size() > 0) {
            return new ResponseEntity<List<TodoDTO>>(todos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No todos available", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/todos")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo) {
        try {
            todo.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todo);
            return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id) {
        Optional<TodoDTO> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            return new ResponseEntity<>(todoOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Todo not found with id " + id, HttpStatus.NOT_FOUND);
        }
    }

}
