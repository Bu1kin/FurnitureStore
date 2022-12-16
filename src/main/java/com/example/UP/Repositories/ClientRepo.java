package com.example.UP.Repositories;

import com.example.UP.Models.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepo extends CrudRepository<Client, Long> {
    public List<Client> findBySurname(String name);
    public List<Client> findBySurnameContaining(String name);
}
