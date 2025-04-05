package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Save or update employee details
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Retrieve all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // Generate company credentials upon approval
    public void generateEmployeeCredentials(Employee employee) {
        String uniqueId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        employee.setEmployeeId("EMP" + uniqueId);
        String email = employee.getFullName().toLowerCase().replaceAll("\\s+", ".") + "@company.com";
        employee.setEmployeeEmail(email);
    }
}
