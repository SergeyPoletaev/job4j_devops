package ru.job4j.devops.service;

import ru.job4j.devops.models.CalcResult;
import ru.job4j.devops.models.TwoArgs;

import java.util.List;

public interface ResultService {

    CalcResult add(TwoArgs args);

    List<CalcResult> findAll();
}
