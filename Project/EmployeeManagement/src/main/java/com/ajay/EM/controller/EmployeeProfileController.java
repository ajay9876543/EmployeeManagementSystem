package com.ajay.EM.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeProfileController {

 @GetMapping("/profile")
 public String profile(Authentication auth) {
 return "Hello " + auth.getName();
 }
}


