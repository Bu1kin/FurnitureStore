package com.example.UP.Repositories;

import com.example.UP.Models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {
    public List<Employee> findBySurname(String name);
    public List<Employee> findBySurnameContaining(String name);
    public Employee findByEmail(String email);
}
