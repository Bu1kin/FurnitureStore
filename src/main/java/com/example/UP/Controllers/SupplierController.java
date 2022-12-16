package com.example.UP.Controllers;

import com.example.UP.Services.SupplierServices;
import com.example.UP.Models.Supplier;
import com.example.UP.Repositories.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
//@PreAuthorize("hasAnyAuthority('ADMIN', 'PURCHASING')")
public class SupplierController {
    @Autowired
    SupplierRepo supplierRepo;

    @Autowired
    SupplierServices service;

    //@PreAuthorize("hasAnyAuthority('ADMIN', 'PURCHASING', 'WAREHOUSE')")
    @GetMapping("/supplier")
    public String main(Model model){
        Iterable<Supplier> listSupplier = supplierRepo.findAll();
        model.addAttribute("listSupplier", listSupplier);
        return "/Supplier/index";
    }

    @PostMapping("/supplier/add")
    public String supplierAdd(@RequestParam String Email,
                              @RequestParam String NameSupplier,
                              @RequestParam String Address,
                              @RequestParam String Phone,
                              Model model){
        Supplier supplier = new Supplier(Email, NameSupplier, Address, Phone);
        supplierRepo.save(supplier);
        return ("redirect:/supplier");
    }

    @GetMapping("/supplier/filter-direct")
    public String supplierFilterDirect(@RequestParam String searchName,
                                       Model model){
        List<Supplier> supplier = supplierRepo.findByNameSupplier(searchName);
        model.addAttribute("supplier", supplier);
        return "/Supplier/filter";
    }

    @GetMapping("/supplier/filter-contains")
    public String supplierFilterContains(@RequestParam String searchName,
                                         Model model){
        List<Supplier> supplier = supplierRepo.findByNameSupplierContaining(searchName);
        model.addAttribute("supplier", supplier);
        return "/Supplier/filter";
    }

    @GetMapping("/supplier/details/{id}")
    public String supplierDetails(Model model, @PathVariable Long id) {
        Supplier supplier = supplierRepo.findById(id).orElseThrow();
        model.addAttribute("supplier", supplier);
        return ("/Supplier/details");
    }

    @GetMapping("/supplier/del/{id}")
    public String supplierDelete(@PathVariable Long id) {
        supplierRepo.deleteById(id);
        return("redirect:/supplier");
    }


    @PostMapping("/supplier/edit/{id}")
    public String supplierEdit(Supplier supplier, BindingResult result) {
        if(result.hasErrors())
            return ("/Supplier/edit");
        supplierRepo.save(supplier);
        return("redirect:/supplier");
    }

    @GetMapping("/supplier/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-ddHH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=supplier" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        response.setHeader(headerKey, headerValue);

        List<Supplier> listSuppliers = service.listAll();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"ID", "E-mail", "Name", "Address", "Phone"};
        String[] nameMapping = {"id", "email", "nameSupplier", "address", "phone"};

        csvWriter.writeHeader(csvHeader);

        for (Supplier supplier : listSuppliers) {
            csvWriter.write(supplier, nameMapping);
        }

        csvWriter.close();
    }
}
