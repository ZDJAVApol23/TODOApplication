package pl.sda.todoapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sda.todoapplication.model.CreateTodoDto;
import pl.sda.todoapplication.model.TodoDto;
import pl.sda.todoapplication.service.TodoService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public String todos(Model model) {

        List<TodoDto> active = todoService.findAllActive();
        List<TodoDto> completed = todoService.findAllCompleted();

        model.addAttribute("active", active);
        model.addAttribute("completed", completed);

        return "todos";
    }

    @RequestMapping(value = "/todo/add", method = RequestMethod.GET)
    public String createTodo(Model model) {

        model.addAttribute("todo", new CreateTodoDto());

        return "createtodo";
    }

    @RequestMapping(value = "/todo/add", method = RequestMethod.POST)
    public String createTodo(@ModelAttribute("todo") @Valid CreateTodoDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "createtodo";
        }

        todoService.create(dto);

        return "redirect:/todo";
    }
}
