package com.example.rule_engine_demo.dto;

import com.example.rule_engine_demo.enums.Designation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long id;

    private String name;
    private double salary;

    private Date doj;

    private String contactNumber;
    private Designation designation;
}
