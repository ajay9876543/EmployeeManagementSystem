package com.ajay.EM.controller;

import com.ajay.EM.entity.Employee;
import com.ajay.EM.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        logger.info("Adding new employee: {}", employee.getName());
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable int id) {
        logger.info("Fetching employee with id: {}", id);
        return employeeService.getEmployee(id);
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees");
        return employeeService.getAllEmployees();
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable int id) {
        logger.info("Updating employee id {}: {}", id, employee.getName());
        return employeeService.updateEmployee(employee, id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        logger.info("Deleting employee with id: {}", id);
        employeeService.deleteEmployee(id);
    }
}
