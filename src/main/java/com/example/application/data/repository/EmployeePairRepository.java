package com.example.application.data.repository;

import com.example.application.data.entity.Employee;
import com.example.application.data.entity.EmployeePair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePairRepository extends JpaRepository<EmployeePair, Integer> {

}
