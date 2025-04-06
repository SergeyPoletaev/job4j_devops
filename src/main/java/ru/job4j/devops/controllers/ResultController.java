package ru.job4j.devops.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.devops.models.CalcResult;
import ru.job4j.devops.models.TwoArgs;
import ru.job4j.devops.service.ResultService;

import java.util.List;

@RestController
@RequestMapping("/calc")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @PostMapping("/add")
    public ResponseEntity<CalcResult> add(@RequestBody TwoArgs args) {
        return ResponseEntity.ok(resultService.add(args));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CalcResult>> all() {
        return ResponseEntity.ok(resultService.findAll());
    }
}
