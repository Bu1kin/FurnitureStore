package com.example.UP.Controllers;

import com.example.UP.Models.*;
import com.example.UP.Repositories.EmployeeRepo;
import com.example.UP.Repositories.PlanRepo;
import com.example.UP.Repositories.ProductRepo;
import com.example.UP.Repositories.SupplierRepo;
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
//@PreAuthorize("hasAnyAuthority('ADMIN', 'PURCHASING', 'WAREHOUSE')")
public class PlanController {
    @Autowired
    PlanRepo planRepo;
    @Autowired
    SupplierRepo supplierRepo;
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    ProductRepo productRepo;

    @GetMapping("/plan")
    public String main(Model model){
        Iterable<Plan> listPlan = planRepo.findAll();
        Iterable<Supplier> listSupplier = supplierRepo.findAll();
        Iterable<Employee> listEmployee = employeeRepo.findAll();
        Iterable<Product> listProduct = productRepo.findAll();

        model.addAttribute("listPlan", listPlan);
        model.addAttribute("listSupplier", listSupplier);
        model.addAttribute("listEmployee", listEmployee);
        model.addAttribute("listProduct", listProduct);
        return "/Plan/index";
    }

    @PostMapping("/plan/add")
    public String planAdd(@RequestParam String Date_Of_Registration,
                          @RequestParam Long supplier,
                          @RequestParam Long employee,
                          @RequestParam Long product,
                          Model model){
        Supplier supplier1 = supplierRepo.findById(supplier).orElseThrow();
        Employee employee1 = employeeRepo.findById(employee).orElseThrow();
        Product product1 = productRepo.findById(product).orElseThrow();

        Plan plan = new Plan(Date_Of_Registration, supplier1, employee1, product1);
        plan.setTotalCost(product1.getPrice() * product1.getAmount().doubleValue());
        planRepo.save(plan);
        return ("redirect:/plan");
    }

    @GetMapping("/plan/filter-direct")
    public String planFilterDirect(@RequestParam String searchName,
                                   Model model){
        List<Plan> plan = planRepo.findByDateOfRegistration(searchName);
        model.addAttribute("plan", plan);
        return "/Plan/filter";
    }

    @GetMapping("/plan/details/{id}")
    public String planDetails(Model model, @PathVariable Long id) {
        Plan plan = planRepo.findById(id).orElseThrow();
        Iterable<Supplier> listSupplier = supplierRepo.findAll();
        Iterable<Employee> listEmployee = employeeRepo.findAll();
        Iterable<Product> listProduct = productRepo.findAll();

        model.addAttribute("plan", plan);
        model.addAttribute("listSupplier", listSupplier);
        model.addAttribute("listEmployee", listEmployee);
        model.addAttribute("listProduct", listProduct);

        return ("/Plan/details");
    }

    @GetMapping("plan/del/{id}")
    public String planDelete(@PathVariable Long id) {
        planRepo.deleteById(id);
        return("redirect:/plan");
    }
}
