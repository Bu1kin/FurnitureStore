package com.example.UP.Models;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "Route_")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String timeToDestination;
    //для роли курьера
    @ManyToMany
    @JoinTable(name = "employee_route",
            joinColumns = @JoinColumn(name = "ID_Route"),
            inverseJoinColumns = @JoinColumn(name = "ID_Employee"))
    private List<Employee> employee;

    public Route(String address, String timeToDestination){
        this.address = address;
        this.timeToDestination = timeToDestination;
    }

    public Route() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimeToDestination() {
        return timeToDestination;
    }

    public void setTimeToDestination(String timeToDestination) {
        this.timeToDestination = timeToDestination;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
}
