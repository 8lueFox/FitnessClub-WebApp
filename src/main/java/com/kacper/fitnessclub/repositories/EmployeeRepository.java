package com.kacper.fitnessclub.repositories;

import com.kacper.fitnessclub.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
