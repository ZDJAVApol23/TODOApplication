package pl.sda.todoapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.todoapplication.entity.TodoEntity;
import pl.sda.todoapplication.mapper.TodoMapper;
import pl.sda.todoapplication.model.CreateTodoDto;
import pl.sda.todoapplication.model.TodoDto;
import pl.sda.todoapplication.repository.TodoRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public TodoDto getById(Long id) {

        Optional<TodoEntity> entity = todoRepository.findById(id);
        if (entity.isPresent()) {
            return TodoMapper.mapeEntityToDto(entity.get());
        }

        throw new EntityNotFoundException();
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

    public TodoDto complete(Long id) {
        Optional<TodoEntity> entity = todoRepository.findById(id);
        if (entity.isPresent()) {
            TodoEntity todoEntity = entity.get();
            todoEntity.setCompleted(true);
            todoEntity.setCompleteDate(new Date());

            todoRepository.save(todoEntity);
            return TodoMapper.mapeEntityToDto(todoEntity);
        }

        throw new EntityNotFoundException();
    }
}
