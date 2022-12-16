package com.example.UP.Controllers;

import com.example.UP.Models.Employee;
import com.example.UP.Models.Post;
import com.example.UP.Repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
//@PreAuthorize("hasAnyAuthority('ADMIN')")
public class EmployeeController {
    @Autowired
    EmployeeRepo employeeRepo;

    @GetMapping("/employee")
    public String main(Model model){
        Iterable<Employee> listEmployee = employeeRepo.findAll();

        model.addAttribute("listEmployee", listEmployee);
        return "/Employee/index";
    }

    @GetMapping("/employee/filter-direct")
    public String employeeFilterDirect(@RequestParam String searchName,
                                       Model model){
        List<Employee> employee = employeeRepo.findBySurname(searchName);
        model.addAttribute("employee", employee);
        return "/Employee/filter";
    }

    @GetMapping("/employee/filter-contains")
    public String employeeFilterContains(@RequestParam String searchName,
                                         Model model){
        List<Employee> employee = employeeRepo.findBySurnameContaining(searchName);
        model.addAttribute("employee", employee);
        return "/Employee/filter";
    }

    @GetMapping("/employee/details/{id}")
    public String employeeDetails(Model model, @PathVariable Long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow();
        model.addAttribute("employee", employee);
        model.addAttribute("listPost", Post.values());
        return ("/Employee/details");
    }

    @GetMapping("/employee/del/{id}")
    public String employeeDelete(@PathVariable Long id) {
        employeeRepo.deleteById(id);
        return("redirect:/employee");
    }

    @PostMapping("/employee/edit/{id}")
    public String employeeEdit(@PathVariable Long id,
                               @RequestParam String email,
                               @RequestParam String surname,
                               @RequestParam String name,
                               @RequestParam String middleName,
                               @RequestParam String phone,
                               @RequestParam String[] posts) {
        Employee employee = employeeRepo.findById(id).orElseThrow();
        employee.setEmail(email);
        employee.setSurname(surname);
        employee.setName(name);
        employee.setMiddleName(middleName);
        employee.setPhone(phone);
        employee.getPost().clear();

        for(String post: posts){
            employee.getPost().add(Post.valueOf(post));
        }

        employeeRepo.save(employee);
        return("redirect:/employee");
    }

    @GetMapping("/employee/stats")
    public String getPie(Model model){
        return ("Employee/stats");
    }
}
