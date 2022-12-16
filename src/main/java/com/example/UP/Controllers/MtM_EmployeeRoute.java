package com.example.UP.Controllers;

import com.example.UP.Models.Employee;
import com.example.UP.Models.Route;
import com.example.UP.Repositories.EmployeeRepo;
import com.example.UP.Repositories.RouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MtM_EmployeeRoute {
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    RouteRepo routeRepo;

    @GetMapping("/employee_route")
    public String employeeRouteView(Model model){
        Iterable<Employee> listEmployee = employeeRepo.findAll();
        Iterable<Route> listRoute = routeRepo.findAll();

        model.addAttribute("listEmployee", listEmployee);
        model.addAttribute("listRoute", listRoute);
        return "/Route/index";
    }

    @GetMapping("/employee_route/add")
    public String employeeRouteAddView(Model model){
        Iterable<Employee> listEmployee = employeeRepo.findAll();
        Iterable<Route> listRoute = routeRepo.findAll();

        model.addAttribute("listEmployee", listEmployee);
        model.addAttribute("listRoute", listRoute);
        return "/EmployeeRoute/add";
    }

    @PostMapping("/employee_route/add")
    public String employeeRouteAdd(@RequestParam String employeeInfo, @RequestParam String routeInfo, Model model){
        Employee employee = employeeRepo.findById(Long.valueOf(employeeInfo.split(" ")[0])).orElseThrow();
        Route route = routeRepo.findById(Long.valueOf(routeInfo.split(" ")[0])).orElseThrow();

        List<Route> routeList = employee.getRoute();
        routeList.add(route);

        employee.setRoute(routeList);
        employeeRepo.save(employee);
        return ("redirect:/employee_route");
    }
}
