package com.example.UP.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Entity
@Table(name = "Supplier_")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nameSupplier;
    private String address;
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
    private Collection<Product> product;
    @JsonIgnore
    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
    private Collection<Plan> plan;

    public Supplier(String email, String nameSupplier, String address, String phone){
        this.email = email;
        this.nameSupplier = nameSupplier;
        this.address = address;
        this.phone = phone;
    }

    public Supplier(Long id, String email, String nameSupplier, String address, String phone){
        this.id = id;
        this.email = email;
        this.nameSupplier = nameSupplier;
        this.address = address;
        this.phone = phone;
    }

    public Supplier() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameSupplier() {
        return nameSupplier;
    }

    public void setNameSupplier(String nameSupplier) {
        this.nameSupplier = nameSupplier;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Collection<Product> getProduct() {
        return product;
    }

    public void setProduct(Collection<Product> product) {
        this.product = product;
    }

    public Collection<Plan> getPlan() {
        return plan;
    }

    public void setPlan(Collection<Plan> plan) {
        this.plan = plan;
    }
}
