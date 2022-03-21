package com.example.controllers;

import com.example.Taskdto.EmployeeDTO;
import com.example.entities.TaskDTO.Employee;
import com.example.repositories.Task.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/search/employee/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id){
        return this.employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/Name/{name}")
    public ResponseEntity<EmployeeDTO> findByName(@PathVariable String name){
        return (ResponseEntity<EmployeeDTO>) this.employeeRepository.findByName(name).stream()
                .map(ResponseEntity::ok);
    }
}
