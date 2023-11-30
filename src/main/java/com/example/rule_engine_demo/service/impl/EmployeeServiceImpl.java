package com.example.rule_engine_demo.service.impl;


import com.example.rule_engine_demo.dto.ResponseDto;
import com.example.rule_engine_demo.entity.Employee;
import com.example.rule_engine_demo.repoistory.EmployeeRepo;
import com.example.rule_engine_demo.service.EmployeeService;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

     public String saveEmployee(Employee employee){
         employeeRepo.save(employee);
         return "saved";
     }

     public List<Employee> getAllEmployees(){
         return employeeRepo.findAll();
     }

     public ResponseDto getYearlySalary(Long id){
         Optional<Employee> employee = employeeRepo.findById(id);
         Employee employee1 = employee.get();
         ResponseDto responseDto=new ResponseDto();
         KieSession kieSession = kiaSession("files/salary.drl");
         kieSession.insert(employee1);
         kieSession.setGlobal("responseDto",responseDto);
         kieSession.insert(employee1);
         kieSession.fireAllRules();
         kieSession.dispose();

         return responseDto;

     }

     public List<ResponseDto> getAllEmployeeYearlySalary(){
         List<Employee> employeeList = new ArrayList<>();
         employeeList = employeeRepo.findAll();
         List<ResponseDto> responseDtoList = new ArrayList<>();
         KieSession kieSession = kiaSession("files/salaries.drl");
         kieSession.insert(employeeList);
         kieSession.setGlobal("responseDtoList",responseDtoList);
         kieSession.insert(employeeList);
         kieSession.fireAllRules();
         kieSession.dispose();

         return responseDtoList;
     }
    public KieSession kiaSession(String rdlFile){
        KieFileSystem kieFileSystem = getKieFileSystem(rdlFile);
        KieBuilder kieBuilder = KieServices.Factory.get().newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        KieModule kieModule = kieBuilder.getKieModule();
        KieContainer kieContainer = KieServices.Factory.get().newKieContainer(kieModule.getReleaseId());
        KieSession kieSession = kieContainer.newKieSession();
        return kieSession;
    }
    private KieFileSystem getKieFileSystem(String rdlFile) {
        KieFileSystem kieFileSystem = KieServices.Factory.get().newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(rdlFile));
        return kieFileSystem;
    }



}
