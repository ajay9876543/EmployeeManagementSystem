package com.ajay.EM;

import com.ajay.EM.dao.EmployeeRepository;
import com.ajay.EM.entity.Employee;
import com.ajay.EM.service.EmployeeService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testAddEmployee() {
        Employee emp = new Employee();
        emp.setName("John");
        emp.setEmail("john@example.com");

        when(employeeRepository.save(emp)).thenReturn(emp);

        Employee saved = employeeService.addEmployee(emp);

        assertEquals("John", saved.getName());
        verify(employeeRepository, times(1)).save(emp);
    }

    @Test
    void testGetEmployee() {
        Employee emp = new Employee();
        emp.setId(1);
        emp.setName("John");

        when(employeeRepository.findById(1)).thenReturn(Optional.of(emp));

        Employee found = employeeService.getEmployee(1);

        assertEquals(1, found.getId());
        assertEquals("John", found.getName());
        verify(employeeRepository, times(1)).findById(1);
    }
}
