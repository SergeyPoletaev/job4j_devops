package ru.job4j.devops.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.devops.config.ContainersConfig;
import ru.job4j.devops.enums.Operation;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.UserRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CalcServiceTest extends ContainersConfig {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CalcService calcService;

    @Test
    public void whenSaveCalcEvent() {
        var user = new User();
        user.setName("Job4j");
        userRepository.save(user);

        var rsl = calcService.add(user, 1, 2);

        assertNotNull(rsl.getId());
        assertThat(rsl.getUser().getName()).isEqualTo("Job4j");
        assertThat(rsl.getResult()).isEqualTo(3.0);
        assertThat(rsl.getType()).isEqualTo(Operation.ADD.name());
    }
}
