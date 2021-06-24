package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import repository.TodoRepository;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;
}
