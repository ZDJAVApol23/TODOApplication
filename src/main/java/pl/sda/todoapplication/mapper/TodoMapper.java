package pl.sda.todoapplication.mapper;

import pl.sda.todoapplication.entity.TodoEntity;
import pl.sda.todoapplication.model.TodoDto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TodoMapper {

    public static List<TodoDto> mapEntityListToDtoList(List<TodoEntity> entities) {

        List<TodoDto> result = new ArrayList<>();

        for (TodoEntity entity : entities) {
            TodoDto dto = mapeEntityToDto(entity);
            result.add(dto);
        }

        return result;
    }

    public static TodoDto mapeEntityToDto(TodoEntity entity) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return new TodoDto(
                entity.getId(),
                entity.getText(),
                dateFormat.format(entity.getCreateDate()));
    }
}