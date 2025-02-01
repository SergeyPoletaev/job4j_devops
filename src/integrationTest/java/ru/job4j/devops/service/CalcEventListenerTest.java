package ru.job4j.devops.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import ru.job4j.devops.config.ContainersConfig;
import ru.job4j.devops.enums.Operation;
import ru.job4j.devops.models.CalcEvent;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.CalcEventRepository;
import ru.job4j.devops.repository.UserRepository;

import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.awaitility.Awaitility.await;

public class CalcEventListenerTest extends ContainersConfig {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CalcEventRepository calcEventRepository;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    void whenSignupNewEvent() {
        User user = new User();
        user.setName("Job4j");
        userRepository.save(user);
        CalcEvent calcEvent = CalcEvent.builder()
                .user(user)
                .first(1)
                .second(2)
                .result(3.0)
                .type(Operation.ADD.name())
                .build();
        kafkaTemplate.send("calc-event", calcEvent);
        await()
                .pollInterval(Duration.ofSeconds(3))
                .atMost(10, SECONDS)
                .untilAsserted(() -> {
                    var rsl = calcEventRepository.findAll().iterator().next();
                    assertThat(rsl.getUser().getName()).isEqualTo("Job4j");
                });
    }
}
