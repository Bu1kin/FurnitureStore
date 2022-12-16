package com.example.UP.Controllers;

import com.example.UP.Models.Employee;
import com.example.UP.Models.Register;
import com.example.UP.Models.Route;
import com.example.UP.Repositories.EmployeeRepo;
import com.example.UP.Repositories.RouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class RouteController {
    @Autowired
    RouteRepo routeRepo;
    @Autowired
    EmployeeRepo employeeRepo;

    @GetMapping("/route")
    public String main(Model model){
        Iterable<Route> listRoute = routeRepo.findAll();
        Iterable<Employee> listEmployee = employeeRepo.findAll();

        model.addAttribute("listRoute", listRoute);
        model.addAttribute("listEmployee", listEmployee);
        return "/Route/index";
    }

    @PostMapping("/route/add")
    public String routeAdd(@RequestParam String address,
                           @RequestParam String timeToDestination,
                           Model model){

        Route route = new Route(address, timeToDestination);
        routeRepo.save(route);
        return ("redirect:/route");
    }

    @GetMapping("/route/details/{id}")
    public String routeDetails(Model model, @PathVariable Long id) {
        Route route = routeRepo.findById(id).orElseThrow();
        Iterable<Employee> listEmployee = employeeRepo.findAll();

        model.addAttribute("route", route);
        model.addAttribute("listEmployee", listEmployee);
        return ("/Route/details");
    }

    @PostMapping("/route/edit/{id}")
    public String routeEdit(Route route, Model model) {
        routeRepo.save(route);
        return("redirect:/route");
    }

    @GetMapping("/route/del/{id}")
    public String routeDelete(@PathVariable Long id) {
        routeRepo.deleteById(id);
        return("redirect:/route");
    }

    @GetMapping("/route/filter-contains")
    public String routeFilterContains(@RequestParam String searchName,
                                      Model model){
        List<Route> listRoute = routeRepo.findByAddressContaining(searchName);
        model.addAttribute("listRoute", listRoute);
        return "/Route/filter";
    }
}
