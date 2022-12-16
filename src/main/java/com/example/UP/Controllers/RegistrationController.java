package com.example.UP.Controllers;

import com.example.UP.Models.Employee;
import com.example.UP.Models.Post;
import com.example.UP.Repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.awt.*;
import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String RegView(Employee employee, Model model){
        return "/Securing/registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid Employee employee, Model model, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return("/Securing/registration");
        }
        if(employeeRepo.findByEmail(employee.getEmail()) != null){
           model.addAttribute("error", "Такой пользователь уже существует!");
           return "/Securing/registration";
        }

        employee.setPost(Collections.singleton(Post.Нет));
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setActive(true);

        employeeRepo.save(employee);
        return "redirect:/login";
    }
}
