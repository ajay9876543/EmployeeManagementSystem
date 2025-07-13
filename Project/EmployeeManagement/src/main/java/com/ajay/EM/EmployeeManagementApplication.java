package com.ajay.EM;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ajay.EM.dao.UserRepository;
import com.ajay.EM.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EmployeeManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }

    // ✅ This bean runs ONCE when the app starts
    @Bean
    CommandLineRunner init(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("admin") == null) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole("ADMIN");
                repo.save(admin);
            }

            if (repo.findByUsername("manager") == null) {
                User manager = new User();
                manager.setUsername("manager");
                manager.setPassword(encoder.encode("manager123"));
                manager.setRole("MANAGER");
                repo.save(manager);
            }

            if (repo.findByUsername("employee") == null) {
                User employee = new User();
                employee.setUsername("employee");
                employee.setPassword(encoder.encode("emp123"));
                employee.setRole("EMPLOYEE");
                repo.save(employee);
            }
        };
    }
}
