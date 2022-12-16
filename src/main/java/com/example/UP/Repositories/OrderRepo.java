package com.example.UP.Repositories;

import com.example.UP.Models.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepo extends CrudRepository<Order, Long> {
    public List<Order> findByDateOfRelease(String name);
    public List<Order> findByDateOfReleaseContaining(String name);
}
