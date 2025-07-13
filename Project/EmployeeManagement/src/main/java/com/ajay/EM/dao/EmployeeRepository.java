package com.ajay.EM.dao;

import com.ajay.EM.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e.department, COUNT(e) FROM Employee e GROUP BY e.department")
    List<Object[]> countByDepartment();

    @Query("SELECT e.jobtitle, COUNT(e) FROM Employee e GROUP BY e.jobtitle")
    List<Object[]> countByJobtitle();

    List<Employee> findByDepartment(String department);
    List<Employee> findByJobtitle(String jobtitle);
}
