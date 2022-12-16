package com.example.UP.APIRepos;

import com.example.UP.Models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APISupplierRepo extends JpaRepository<Supplier, Long> {
}
