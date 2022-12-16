package com.example.UP.Models;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Plan_")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dateOfRegistration;
    @ManyToOne(optional = true)
    private Supplier supplier;
    @ManyToOne(optional = true)
    private Employee employee;
    @ManyToOne(cascade = CascadeType.DETACH)
    private Product product;
    private Double totalCost;

    public Plan(String dateOfRegistration, Supplier supplier, Employee employee, Product product){
        this.dateOfRegistration = dateOfRegistration;
        this.supplier = supplier;
        this.employee = employee;
        this.product = product;
    }

    public Plan() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
