package com.example.UP.API;

import com.example.UP.Models.Supplier;
import com.example.UP.Securing.MessageResponse;
import com.example.UP.Services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/supplier")
public class APISupplierController {
    @Autowired
    SupplierService supplierService;

    @GetMapping("/suppliers")
    public ResponseEntity<List<Supplier>> getAllSuppliers(){
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable("id") Long id){
        Supplier supplier = supplierService.getASingleSupplier(id);
        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Supplier>> updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier){
        Optional<Supplier> updateSupplier = supplierService.updateSupplier(id, supplier);
        return new ResponseEntity<>(updateSupplier, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> createSupplier(@RequestBody Supplier supplier){
        MessageResponse newSupplier = supplierService.createSupplier(supplier);
        return new ResponseEntity<>(newSupplier, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id){
        supplierService.deleteSupplier(id);
    }
}
