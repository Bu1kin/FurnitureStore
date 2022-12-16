package com.example.UP.Models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameProduct;
    private Integer amount;
    private Double weight;
    private Double price;
    private String dateOfProduction;
    @ManyToOne(cascade = CascadeType.ALL)
    private Supplier supplier;
    @ManyToOne(cascade = CascadeType.ALL)
    private Warehouse warehouse;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Collection<Plan> plan;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Collection<Order> order;

    public Product(String nameProduct, Integer amount, Double weight, Double price, String dateOfProduction, Supplier supplier, Warehouse warehouse){
        this.nameProduct = nameProduct;
        this.amount = amount;
        this.weight = weight;
        this.price = price;
        this.dateOfProduction = dateOfProduction;
        this.supplier = supplier;
        this.warehouse = warehouse;
    }

    public Product() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDateOfProduction() {
        return dateOfProduction;
    }

    public void setDateOfProduction(String dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Collection<Order> getOrder() {
        return order;
    }

    public void setOrder(Collection<Order> order) {
        this.order = order;
    }

    public Collection<Plan> getPlan() {
        return plan;
    }

    public void setPlan(Collection<Plan> plan) {
        this.plan = plan;
    }
}
