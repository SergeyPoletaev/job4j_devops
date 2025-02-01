package ru.job4j.devops.service;

import ru.job4j.devops.models.CalcEvent;

public interface CalcEventListener {

    void signup(CalcEvent calcEvent);
}
