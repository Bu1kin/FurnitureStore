package com.example.UP.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Check_")
public class Check {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(optional = true)
    @JoinColumn(name="ID_Order")
    private Order order;
    private String dateOfRelease;
    @ManyToOne(optional = true)
    private Employee employee;
    @ManyToOne(optional = true)
    private Client client;
    @ManyToOne(optional = true)
    private Register register;
    private Double totalCost;

    public Check(Order order, String dateOfRelease, Employee employee, Client client, Register register){
        this.dateOfRelease = dateOfRelease;
        this.order = order;
        this.employee = employee;
        this.client = client;
        this.register = register;
    }

    public Check() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(String dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }
}
