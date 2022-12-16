package com.example.UP.Repositories;

import com.example.UP.Models.Warehouse;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WarehouseRepo extends CrudRepository<Warehouse, Long> {
    public Warehouse findByAddress(String address);
    public List<Warehouse> findByAddressContaining(String address);
}
