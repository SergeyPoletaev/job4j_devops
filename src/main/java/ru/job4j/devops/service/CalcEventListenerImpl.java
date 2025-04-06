package ru.job4j.devops.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.devops.models.CalcEvent;
import ru.job4j.devops.repository.CalcEventRepository;

@Profile("kafka")
@Slf4j
@Service
@RequiredArgsConstructor
public class CalcEventListenerImpl implements CalcEventListener {
    private final CalcEventRepository repository;

    @KafkaListener(topics = "calc-event", groupId = "job4j")
    @Override
    public void signup(CalcEvent calcEvent) {
        log.info("---> Регистрация события {} от пользователя {}", calcEvent.getType(), calcEvent.getUser().getName());
        repository.save(calcEvent);
    }
}
