package com.example.UP.Services;

import com.example.UP.Models.Supplier;
import com.example.UP.Repositories.JpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServices {
    @Autowired
    JpaRepo jpaRepo;
    public List<Supplier> listAll() {
        return jpaRepo.findAll(Sort.by("email").ascending());
    }
}
