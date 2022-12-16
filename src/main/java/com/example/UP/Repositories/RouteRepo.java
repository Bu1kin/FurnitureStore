package com.example.UP.Repositories;

import com.example.UP.Models.Route;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RouteRepo extends CrudRepository<Route, Long> {
    public List<Route> findByAddressContaining(String address);
}
