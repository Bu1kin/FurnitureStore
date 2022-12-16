package com.example.UP.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "Register_")
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date datePurchase;
    @JsonIgnore
    @ManyToOne(optional = true)
    private Employee employee;

    @JsonIgnore
    @OneToMany(mappedBy = "register", fetch = FetchType.LAZY)
    private Collection<Check> checks;

    public Register(Date datePurchase, Employee employee){
        this.datePurchase = datePurchase;
        this.employee = employee;
    }

    public Register() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(Date datePurchase) {
        this.datePurchase = datePurchase;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Collection<Check> getChecks() {
        return checks;
    }

    public void setChecks(Collection<Check> checks) {
        this.checks = checks;
    }
}
