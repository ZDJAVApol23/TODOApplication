package pl.sda.todoapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.todoapplication.entity.RoleEntity;
import pl.sda.todoapplication.entity.TodoEntity;
import pl.sda.todoapplication.entity.UserEntity;
import pl.sda.todoapplication.repository.RoleRepository;
import pl.sda.todoapplication.repository.TodoRepository;
import pl.sda.todoapplication.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

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

        Iterable<UserEntity> users = userRepository.findAll();
        boolean hasAdmin = false;

        for (UserEntity userEntity : users) {
            for (RoleEntity roleEntity : userEntity.getRoles()) {
                if (roleEntity.getName().equals("ADMIN")) {
                    hasAdmin = true;
                    break;
                }
            }
        }

        if (!hasAdmin) {
            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setFirstName("ADMIN");
            admin.setLastName("ADMIN");

            Collection<RoleEntity> roles = new ArrayList<>();
            for (RoleEntity roleEntity : roleRepository.findAll()) {
                roles.add(roleEntity);
            }

            admin.setRoles(roles);

            userRepository.save(admin);
        }
    }
}
