package ru.job4j.devops.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.devops.config.ContainersConfig;
import ru.job4j.devops.models.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserRepositoryTest extends ContainersConfig {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenSaveUser() {
        var user = new User();
        user.setName("Job4j");
        userRepository.save(user);
        var foundUser = userRepository.findById(user.getId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("Job4j");
    }
}
