package com.example.rule_engine_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {


    private Long id;

    private String name;
    private double salary;

    private double yearlySalary;
}
