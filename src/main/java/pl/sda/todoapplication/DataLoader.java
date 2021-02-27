package pl.sda.todoapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.sda.todoapplication.entity.TodoEntity;
import pl.sda.todoapplication.repository.TodoRepository;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (!todoRepository.findAll().iterator().hasNext()) {
            for (int i = 0; i < 10; i++) {

                TodoEntity entity = new TodoEntity("Zadanie zakończone nr " + (i + 1));
                entity.setCompleted(true);

                todoRepository.save(entity);

                entity = new TodoEntity("Zadanie niezakończone nr " + (i + 1));
                todoRepository.save(entity);
            }
        }
    }
}
