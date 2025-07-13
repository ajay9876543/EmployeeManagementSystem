package com.ajay.EM.controller;

import com.ajay.EM.dao.EmployeeRepository;
import com.ajay.EM.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    @Autowired
    private EmployeeRepository repo;

    @GetMapping("/reports")
    public Map<String, Object> reports() {
        Map<String, Object> map = new HashMap<>();
        map.put("total", repo.count());
        map.put("byDept", repo.countByDepartment());
        map.put("byJob", repo.countByJobtitle());
        return map;
    }

    @GetMapping("/filter")
    public List<Employee> filter(@RequestParam(required = false) String department,
                                 @RequestParam(required = false) String jobtitle) {
        if (department != null) return repo.findByDepartment(department);
        if (jobtitle != null) return repo.findByJobtitle(jobtitle);
        return repo.findAll();
    }
}
