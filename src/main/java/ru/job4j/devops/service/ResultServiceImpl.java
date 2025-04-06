package ru.job4j.devops.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.devops.enums.Operation;
import ru.job4j.devops.models.CalcResult;
import ru.job4j.devops.models.TwoArgs;
import ru.job4j.devops.repository.ResultRepository;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository repository;

    @Override
    public CalcResult add(TwoArgs args) {
        double first = args.getFirst();
        double second = args.getSecond();
        CalcResult calcResult = CalcResult.builder()
                .first(first)
                .second(second)
                .result(first + second)
                .type(Operation.ADD.name())
                .createDate(LocalDate.now())
                .build();
        log.info("---> Сохраняем результат вычисления суммы {} + {}", first, second);
        return repository.save(calcResult);
    }

    @Override
    public List<CalcResult> findAll() {
        log.info("---> Получаем все результаты всех вычислений");
        return repository.findAll();
    }
}
