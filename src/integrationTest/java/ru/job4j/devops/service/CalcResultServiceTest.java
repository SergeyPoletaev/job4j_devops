package ru.job4j.devops.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.devops.config.ContainersConfig;
import ru.job4j.devops.enums.Operation;
import ru.job4j.devops.models.CalcResult;
import ru.job4j.devops.models.TwoArgs;
import ru.job4j.devops.repository.ResultRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CalcResultServiceTest extends ContainersConfig {

    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private ResultService resultService;

    @BeforeEach
    public void cleanDb() {
        resultRepository.deleteAll();
    }

    @Test
    public void whenSaveCalcResult() {
        CalcResult rsl = resultService.add(new TwoArgs(1.0, 2.0));
        assertNotNull(rsl.getId());
        assertThat(rsl.getResult()).isEqualTo(3.0);
        assertThat(rsl.getType()).isEqualTo(Operation.ADD.name());
    }

    @Test
    public void whenFindAllCalcResults() {
        resultService.add(new TwoArgs(1.0, 2.0));
        List<CalcResult> results = resultService.findAll();
        assertThat(results.size()).isEqualTo(1);
    }
}
