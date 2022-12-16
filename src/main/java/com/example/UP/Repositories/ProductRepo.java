package com.example.UP.Repositories;

import com.example.UP.Models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {
    public List<Product> findByNameProduct(String name);
    public List<Product> findByNameProductContaining(String name);
}
