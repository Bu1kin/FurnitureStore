package com.example.UP.Controllers;

import com.example.UP.Models.*;
import com.example.UP.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
//@PreAuthorize("hasAnyAuthority('ADMIN')")
public class CheckController {
    @Autowired
    CheckRepo checkRepo;
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    ClientRepo clientRepo;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    RegisterRepo registerRepo;

    //@PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER')")
    @GetMapping("/check")
    public String main(Model model){
        Iterable<Check> listCheck = checkRepo.findAll();
        Iterable<Employee> listEmployee = employeeRepo.findAll();
        Iterable<Client> listClient = clientRepo.findAll();
        Iterable<Order> listOrder = orderRepo.findAll();
        Iterable<Register> listRegister = registerRepo.findAll();
        ArrayList<Order> orderArrayList = new ArrayList<>();

        for(Order temp : listOrder){
            if(temp.getCheck() == null){
                orderArrayList.add(temp);
            }
        }

        model.addAttribute("listCheck", listCheck);
        model.addAttribute("listEmployee", listEmployee);
        model.addAttribute("listClient", listClient);
        model.addAttribute("orderArrayList", orderArrayList);
        model.addAttribute("listRegister", listRegister);
        return "/Check/index";
    }

    //@PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER')")
    @PostMapping("/check/add")
    public String checkAdd(@RequestParam String order,
                           @RequestParam String dateOfRelease,
                           @RequestParam String employee,
                           @RequestParam String client,
                           @RequestParam String register,
                           Model model){
        Order order1 = orderRepo.findById(Long.valueOf(order.split(" ")[0])).orElseThrow();
        Employee employee1 = employeeRepo.findById(Long.valueOf(employee.split(" ")[0])).orElseThrow();
        Client client1 = clientRepo.findById(Long.valueOf(client.split(" ")[0])).orElseThrow();
        Register register1 = registerRepo.findById(Long.valueOf(register.split(" ")[0])).orElseThrow();


        Check check = new Check(order1, dateOfRelease, employee1, client1, register1);
        check.setTotalCost(order1.getTotalCost());
        checkRepo.save(check);
        return ("redirect:/check");
    }

        @GetMapping("/check/details/{id}")
        public String checkDetails(Model model, @PathVariable Long id) {
            Check check = checkRepo.findById(id).orElseThrow();
            Iterable<Employee> listEmployee = employeeRepo.findAll();
            Iterable<Client> listClient = clientRepo.findAll();
            Iterable<Order> listOrder = orderRepo.findAll();
            Iterable<Register> listRegister = registerRepo.findAll();
            ArrayList<Order> orderArrayList = new ArrayList<>();

            for(Order temp : listOrder){
                if(temp.getCheck() == null){
                    orderArrayList.add(temp);
                }
            }

            model.addAttribute("check", check);
            model.addAttribute("listEmployee", listEmployee);
            model.addAttribute("listClient", listClient);
            model.addAttribute("orderArrayList", orderArrayList);
            model.addAttribute("listRegister", listRegister);
        return ("/Check/details");
    }

    //@PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER')")
    @GetMapping("/check/filter-direct")
    public String checkFilterDirect(@RequestParam String searchName,
                                    Model model){
        List<Check> check = checkRepo.findByDateOfRelease(searchName);

        model.addAttribute("check", check);
        return "/Check/filter";
    }

    //@PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER')")
    @GetMapping("/check/filter-contains")
    public String checkFilterContains(@RequestParam String searchName,
                                      Model model){
        List<Check> check = checkRepo.findByDateOfReleaseContaining(searchName);
        model.addAttribute("check", check);
        return "/Check/filter";
    }

    @GetMapping("/check/del/{id}")
    public String checkDelete(@PathVariable Long id) {
        checkRepo.deleteById(id);
        return("redirect:/check");
    }
}
