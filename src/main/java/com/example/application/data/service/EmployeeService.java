package com.example.application.data.service;

import com.example.application.data.entity.Employee;
import com.example.application.data.entity.Project;
import com.example.application.data.repository.EmployeeRepository;
import com.example.application.data.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteAll() {
        employeeRepository.deleteAll();
    }
}
