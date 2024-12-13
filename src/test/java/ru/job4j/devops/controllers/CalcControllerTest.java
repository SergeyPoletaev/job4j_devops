package ru.job4j.devops.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import ru.job4j.devops.models.Result;
import ru.job4j.devops.models.TwoArgs;

import static org.assertj.core.api.Assertions.assertThat;

class CalcControllerTest {

    @Test
    public void whenOnePlusOneThenTwo() {
        var input = new TwoArgs(1, 1);
        var expected = new Result(2);
        var output = new CalcController().summarise(input);
        assertThat(output.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(output.getBody()).isEqualTo(expected);
    }

    @Test
    public void whenNegativeNumber() {
        var input = new TwoArgs(-1, -1);
        var expected = new Result(-2);
        var output = new CalcController().summarise(input);
        assertThat(output.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(output.getBody()).isEqualTo(expected);
    }

    @Test
    public void whenPositiveAndNegativeNumber() {
        var input = new TwoArgs(-1, 1);
        var expected = new Result(0);
        var output = new CalcController().summarise(input);
        assertThat(output.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(output.getBody()).isEqualTo(expected);
    }
}