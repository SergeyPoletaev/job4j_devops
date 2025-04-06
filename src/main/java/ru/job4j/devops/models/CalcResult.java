package ru.job4j.devops.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "calc_result")
public class CalcResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_arg")
    private double first;
    @Column(name = "second_arg")
    private double second;
    @Column(name = "result")
    private double result;
    @Column(name = "create_date")
    private LocalDate createDate;
    @Column(name = "operation_type")
    private String type;
}
