package ru.job4j.devops.service;

import ru.job4j.devops.models.CalcEvent;
import ru.job4j.devops.models.User;

public interface CalcService {

    CalcEvent add(User user, int first, int second);
}
