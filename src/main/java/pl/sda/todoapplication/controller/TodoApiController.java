package pl.sda.todoapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.todoapplication.model.CreateTodoDto;
import pl.sda.todoapplication.model.TodoDto;
import pl.sda.todoapplication.service.TodoService;

import java.util.List;

@RestController
public class TodoApiController {

    @Autowired
    private TodoService todoService;

    @GetMapping(value = "/api/todo")
    public List<TodoDto> todos() {

        List<TodoDto> active = todoService.findAllActive();
        List<TodoDto> completed = todoService.findAllCompleted();

        return active;
    }

    @PostMapping(value = "/api/todo")
    public TodoDto todo(@RequestBody TodoDto todo) {

        CreateTodoDto createTodoDto = new CreateTodoDto();
        createTodoDto.setText(todo.getText());
        createTodoDto.setUserId(4);

        return todoService.save(createTodoDto);
    }
}
