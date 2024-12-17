package ru.job4j.devops.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Контейнер двух аргументов
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TwoArgs {
    private double first;
    private double second;
}
