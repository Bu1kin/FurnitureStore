package com.example.UP.Repositories;

import com.example.UP.Models.Check;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CheckRepo extends CrudRepository<Check, Long> {
    public List<Check> findByDateOfRelease(String name);
    public List<Check> findByDateOfReleaseContaining(String name);
}
