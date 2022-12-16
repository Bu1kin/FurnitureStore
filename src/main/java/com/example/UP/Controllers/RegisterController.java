package com.example.UP.Controllers;

import com.example.UP.Models.Employee;
import com.example.UP.Models.Register;
import com.example.UP.Repositories.EmployeeRepo;
import com.example.UP.Repositories.RegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class RegisterController {
    @Autowired
    RegisterRepo registerRepo;
    @Autowired
    EmployeeRepo employeeRepo;

    @GetMapping("/register")
    public String main(Model model){
        Iterable<Register> listRegister = registerRepo.findAll();
        Iterable<Employee> listEmployee = employeeRepo.findAll();

        model.addAttribute("listRegister", listRegister);
        model.addAttribute("listEmployee", listEmployee);
        return "Register/index";
    }

    @PostMapping("/register/add")
    public String registerAdd(@RequestParam Date datePurchase,
                              @RequestParam String employee,
                              Model model){
        Employee employee1 = employeeRepo.findById(Long.valueOf(employee.split(" ")[0])).orElseThrow();

        Register register = new Register(datePurchase, employee1);
        registerRepo.save(register);
        return ("redirect:/register");
    }

    @GetMapping("/register/details/{id}")
    public String registerDetails(Model model, @PathVariable Long id) {
        Register register = registerRepo.findById(id).orElseThrow();
        Iterable<Employee> listEmployee = employeeRepo.findAll();

        model.addAttribute("register", register);
        model.addAttribute("listEmployee", listEmployee);
        return ("/Register/details");
    }

    @GetMapping("/register/del/{id}")
    public String registerDelete(@PathVariable Long id) {
        registerRepo.deleteById(id);
        return("redirect:/register");
    }
}
