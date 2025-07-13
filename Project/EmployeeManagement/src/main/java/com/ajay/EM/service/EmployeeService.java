package com.ajay.EM.service;

import com.ajay.EM.dao.EmployeeRepository;
import com.ajay.EM.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {
        logger.info("Saving new employee: {}", employee.getName());
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(int id) {
        logger.info("Getting employee by id: {}", id);
        Optional<Employee> optional = employeeRepository.findById(id);
        return optional.orElse(null);
    }

    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees");
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Employee employee, int id) {
        logger.info("Updating employee with id: {}", id);
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(int id) {
        logger.info("Deleting employee with id: {}", id);
        employeeRepository.deleteById(id);
    }
}
