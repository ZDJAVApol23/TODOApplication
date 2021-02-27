package pl.sda.todoapplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.todoapplication.entity.TodoEntity;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<TodoEntity, Long> {

    List<TodoEntity> findAllByCompleted(boolean completed);
}
