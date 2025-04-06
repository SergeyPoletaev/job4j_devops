package ru.job4j.devops.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.devops.models.CalcResult;

import java.util.List;

public interface ResultRepository extends CrudRepository<CalcResult, Long> {

    @Override
    List<CalcResult> findAll();
}
