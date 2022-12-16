package com.example.UP.Repositories;

import com.example.UP.Models.Plan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlanRepo extends CrudRepository<Plan, Long> {
    public List<Plan> findByDateOfRegistration(String name);
    public List<Plan> findByDateOfRegistrationContaining(String name);
}
