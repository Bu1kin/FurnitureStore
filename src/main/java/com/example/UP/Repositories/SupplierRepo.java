package com.example.UP.Repositories;

import com.example.UP.Models.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {
    public List<Supplier> findByNameSupplier(String name);
    //public Supplier findByNameSupplier(String name);
    public List<Supplier> findByNameSupplierContaining(String name);

    public Supplier findByEmail(String email);
}
