package com.example.UP.Controllers;

import com.example.UP.Models.Employee;
import com.example.UP.Models.Route;
import com.example.UP.Models.Supplier;
import com.example.UP.Models.Warehouse;
import com.example.UP.Repositories.EmployeeRepo;
import com.example.UP.Repositories.WarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
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
public class WarehouseController {
    @Autowired
    WarehouseRepo warehouseRepo;
    @Autowired
    EmployeeRepo employeeRepo;

    @GetMapping("/warehouse")
    public String main(Model model){
        Iterable<Warehouse> listWarehouse = warehouseRepo.findAll();
        Iterable<Employee> listEmployee = employeeRepo.findAll();
        ArrayList<Employee> employeeArrayList = new ArrayList<>();

        for(Employee temp : listEmployee){
            if(temp.getWarehouse() == null){
                employeeArrayList.add(temp);
            }
        }

        model.addAttribute("listWarehouse", listWarehouse);
        model.addAttribute("employeeArrayList", employeeArrayList);
        return "Warehouse/index";
    }

    @PostMapping("/warehouse/add")
    public String warehouseAdd(@RequestParam String address,
                               @RequestParam Integer capacity,
                               @RequestParam String employee,
                               Model model){
        Employee employee1 = employeeRepo.findById(Long.valueOf(employee.split(" ")[0])).orElseThrow();

        Warehouse warehouse = new Warehouse(address, capacity, employee1);
        warehouseRepo.save(warehouse);
        return ("redirect:/warehouse");
    }

    @GetMapping("/warehouse/filter-contains")
    public String warehouseFilterContains(@RequestParam String searchName,
                                          Model model){
        List<Warehouse> warehouse = warehouseRepo.findByAddressContaining(searchName);
        model.addAttribute("warehouse", warehouse);
        return "/Warehouse/filter";
    }

    @GetMapping("/warehouse/details/{id}")
    public String warehouseDetails(Model model, @PathVariable Long id) {
        Warehouse warehouse = warehouseRepo.findById(id).orElseThrow();
        Iterable<Employee> listEmployee = employeeRepo.findAll();
        ArrayList<Employee> employeeArrayList = new ArrayList<>();

        for(Employee temp : listEmployee){
            if(temp.getWarehouse() == null){
                employeeArrayList.add(temp);
            }
        }

        model.addAttribute("warehouse", warehouse);
        model.addAttribute("employeeArrayList", employeeArrayList);
        return ("/Warehouse/details");
    }

    @PostMapping("/warehouse/edit/{id}")
    public String warehouseEdit(Warehouse warehouse, Model model) {
        warehouseRepo.save(warehouse);
        return("redirect:/warehouse");
    }

    @GetMapping("/warehouse/del/{id}")
    public String warehouseDelete(@PathVariable Long id) {
        warehouseRepo.deleteById(id);
        return("redirect:/warehouse");
    }
}
