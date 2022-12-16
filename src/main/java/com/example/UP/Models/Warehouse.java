package com.example.UP.Models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Warehouse_")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private Integer capacity;

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.EAGER)
    private Collection<Product> product;
    @OneToOne(optional = true)
    @JoinColumn(name="ID_Employee")
    private Employee employee;

    public Warehouse(String address, Integer capacity, Employee employee){
        this.address = address;
        this.capacity = capacity;
        this.employee = employee;
    }

    public Warehouse() {}

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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Collection<Product> getProduct() {
        return product;
    }

    public void setProduct(Collection<Product> product) {
        this.product = product;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
