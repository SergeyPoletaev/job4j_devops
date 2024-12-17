package ru.job4j.devops.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.devops.models.Result;
import ru.job4j.devops.models.TwoArgs;

/**
 * Класс для обработки запросов
 */
@RestController
@RequestMapping("calc")
public class CalcController {

    /**
     * Обработчик запроса на сложение двух чисел
     * @param twoArgs контейнер с аргументами для арифметической операции
     * @return результат операции
     */
    @PostMapping("summarise")
    public ResponseEntity<Result> summarise(@RequestBody TwoArgs twoArgs) {
        var result = twoArgs.getFirst() + twoArgs.getSecond();
        return ResponseEntity.ok(new Result(result));
    }
}
