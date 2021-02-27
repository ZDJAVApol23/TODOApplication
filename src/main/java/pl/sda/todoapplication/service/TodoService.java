package pl.sda.todoapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.todoapplication.entity.TodoEntity;
import pl.sda.todoapplication.mapper.TodoMapper;
import pl.sda.todoapplication.model.CreateTodoDto;
import pl.sda.todoapplication.model.TodoDto;
import pl.sda.todoapplication.repository.TodoRepository;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<TodoDto> findAllActive() {

        List<TodoEntity> todoEntities = todoRepository.findAllByCompleted(false);

        // jeśli jest konieczna jakaś dodatkowa logika biznesowa to dodajemy ją w tym miejscu

        List<TodoDto> todoDtos = TodoMapper.mapEntityListToDtoList(todoEntities);

        return todoDtos;
    }

    public List<TodoDto> findAllCompleted() {

        List<TodoEntity> todoEntities = todoRepository.findAllByCompleted(true);

        // jeśli jest konieczna jakaś dodatkowa logika biznesowa to dodajemy ją w tym miejscu

        List<TodoDto> todoDtos = TodoMapper.mapEntityListToDtoList(todoEntities);

        return todoDtos;
    }

    public boolean create(CreateTodoDto todo) {

        TodoEntity entity = new TodoEntity(todo.getText());
        try {
            todoRepository.save(entity);
            return true;
        } catch (Exception exception) {
            // handle exception
            return false;
        }
    }
}
