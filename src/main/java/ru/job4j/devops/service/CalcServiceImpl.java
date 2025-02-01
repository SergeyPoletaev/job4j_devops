package ru.job4j.devops.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.devops.enums.Operation;
import ru.job4j.devops.models.CalcEvent;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.CalcEventRepository;

@Service
@RequiredArgsConstructor
public class CalcServiceImpl implements CalcService {
    private final CalcEventRepository repository;

    @Override
    public CalcEvent add(User user, int first, int second) {
        CalcEvent calcEvent = CalcEvent.builder()
                .user(user)
                .first(first)
                .second(second)
                .result(first + second)
                .type(Operation.ADD.name())
                .build();
        return repository.save(calcEvent);
    }
}
