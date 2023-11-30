package com.example.rule_engine_demo.controller;

import com.example.rule_engine_demo.dto.ResponseDto;
import com.example.rule_engine_demo.entity.Employee;
import com.example.rule_engine_demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public String saveEmployee(@RequestBody  Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/get-all")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    @GetMapping("/get-id/{id}")
    public ResponseDto getYearlySalary(@PathVariable Long id){
        return employeeService.getYearlySalary(id);
    }

    @GetMapping("/get-all-employee-salary")
    public List<ResponseDto> getAllEmployeesSalaries(){
        return employeeService.getAllEmployeeYearlySalary();
    }
}
