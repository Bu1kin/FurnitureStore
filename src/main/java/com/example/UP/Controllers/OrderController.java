package com.example.UP.Controllers;

import com.example.UP.Models.*;
import com.example.UP.Repositories.ClientRepo;
import com.example.UP.Repositories.EmployeeRepo;
import com.example.UP.Repositories.OrderRepo;
import com.example.UP.Repositories.ProductRepo;
import com.example.UP.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
//@PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER')")
public class OrderController {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    ClientRepo clientRepo;
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    ProductRepo productRepo;

    @Autowired
    OrderService orderService;

    @GetMapping("/order")
    public String main(Model model){
        Iterable<Order> listOrder = orderRepo.findAll();
        Iterable<Client> listClient = clientRepo.findAll();
        Iterable<Employee> listEmployee = employeeRepo.findAll();
        Iterable<Product> listProduct = productRepo.findAll();

        model.addAttribute("listOrder", listOrder);
        model.addAttribute("listClient", listClient);
        model.addAttribute("listEmployee", listEmployee);
        model.addAttribute("listProduct", listProduct);
        return "/Order/index";
    }

    @PostMapping("/order/add")
    public String orderAdd(@RequestParam String dateOfRelease,
                           @RequestParam Long client,
                           @RequestParam Long employee,
                           @RequestParam Long product,
                           Model model){
        Client client1 = clientRepo.findById(client).orElseThrow();
        Employee employee1 = employeeRepo.findById(employee).orElseThrow();
        Product product1 = productRepo.findById(product).orElseThrow();

        Order order = new Order(dateOfRelease, client1, employee1, product1);
        order.setTotalCost(product1.getPrice() * product1.getAmount().doubleValue());
        orderRepo.save(order);
        return ("redirect:/order");
    }

    @GetMapping("/order/filter-direct")
    public String orderFilterDirect(@RequestParam String searchName,
                                    Model model){
        List<Order> order = orderRepo.findByDateOfRelease(searchName);
        model.addAttribute("order", order);
        return "/Order/filter";
    }

    @GetMapping("/order/details/{id}")
    public String orderDetails(Model model, @PathVariable Long id) {
        Order order = orderRepo.findById(id).orElseThrow();
        Iterable<Client> listClient = clientRepo.findAll();
        Iterable<Employee> listEmployee = employeeRepo.findAll();
        Iterable<Product> listProduct = productRepo.findAll();

        model.addAttribute("order", order);
        model.addAttribute("listClient", listClient);
        model.addAttribute("listEmployee", listEmployee);
        model.addAttribute("listProduct", listProduct);

        return ("/Order/details");
    }

    @GetMapping("/order/del/{id}")
    public String orderDelete(@PathVariable Long id) {
        orderRepo.deleteById(id);
        return("redirect:/order");
    }

    @GetMapping("/order/stats")
    public String stats(Model model){
        List<String> productName = orderService.getAllOrders().stream().map(x->x.getProduct().getNameProduct()).collect(Collectors.toList());
        List<Double> productCost = orderService.getAllOrders().stream().map(x->x.getProduct().getPrice()).collect(Collectors.toList());
        List<Integer> productAmount = orderService.getAllOrders().stream().map(x->x.getProduct().getAmount()).collect(Collectors.toList());

        model.addAttribute("productName", productName);
        model.addAttribute("productCost", productCost);
        model.addAttribute("productAmount", productAmount);
        return "/Order/stats";
    }
}
