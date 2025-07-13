package com.ajay.EM;

import com.ajay.EM.config.SecurityConfig;
import com.ajay.EM.controller.EmployeeController;
import com.ajay.EM.dao.UserRepository;
import com.ajay.EM.entity.Employee;
import com.ajay.EM.service.EmployeeService;
import com.ajay.EM.service.MyUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
@Import(SecurityConfig.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private MyUserDetailsService userDetailsService;

    @MockBean
    private UserRepository userRepository;

    @Test
    @WithMockUser(username = "admin1", roles = {"ADMIN"})
    void testGetAllEmployees() throws Exception {
        Employee emp1 = new Employee();
        emp1.setName("Ajay");

        Employee emp2 = new Employee();
        emp2.setName("Shetty");

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(emp1, emp2));

        mockMvc.perform(get("/api/admin/employee/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ajay"))
                .andExpect(jsonPath("$[1].name").value("Shetty"));
    }

    @Test
    @WithMockUser(username = "admin1", roles = {"ADMIN"})
    void testGetEmployeeById() throws Exception {
        Employee emp = new Employee();
        emp.setId(1);
        emp.setName("Ajay");

        when(employeeService.getEmployee(1)).thenReturn(emp);

        mockMvc.perform(get("/api/admin/employee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ajay"));
    }
}
