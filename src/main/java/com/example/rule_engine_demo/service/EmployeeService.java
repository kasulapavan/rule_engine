package com.example.rule_engine_demo.service;

import com.example.rule_engine_demo.dto.ResponseDto;
import com.example.rule_engine_demo.entity.Employee;

import java.util.List;

public interface EmployeeService {

     String saveEmployee(Employee employeeDto);
    List<Employee> getAllEmployees();

    ResponseDto getYearlySalary(Long id);
    List<ResponseDto> getAllEmployeeYearlySalary();
}
