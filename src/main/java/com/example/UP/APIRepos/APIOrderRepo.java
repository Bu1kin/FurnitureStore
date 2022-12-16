package com.example.UP.APIRepos;

import com.example.UP.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APIOrderRepo extends JpaRepository<Order, Long> {
}
