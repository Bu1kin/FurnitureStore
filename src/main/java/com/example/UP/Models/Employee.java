package com.example.UP.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotBlank(message = "Поле не должно быть пустым!")
    private String email;
    //@NotBlank(message = "Поле не должно быть пустым!")
    private String password;
    private boolean active;
    //@NotBlank(message = "Поле не должно быть пустым!")
    private String surname;
    //@NotBlank(message = "Поле не должно быть пустым!")
    private String name;
    //@NotBlank(message = "Поле не должно быть пустым!")
    private String middleName;
    //@NotBlank(message = "Поле не должно быть пустым!")
    //@Pattern(regexp = "\\+7\\([0-9][0-9][0-9]\\)[0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]", message = "+7(XXX)XXX-XX-XX")
    private String phone;
    @ElementCollection(targetClass = Post.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "post", joinColumns = @JoinColumn(name = "id_employee"))
    @Enumerated(EnumType.STRING)
    private Set<Post> post;

    @JsonIgnore
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Collection<Register> register;
    @JsonIgnore
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Collection<Plan> plan;
    @JsonIgnore
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Collection<Check> check;
    @JsonIgnore
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Collection<Order> order;
    @JsonIgnore
    @OneToOne(optional = true, mappedBy = "employee")
    private Warehouse warehouse;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "employee_route",
        joinColumns = @JoinColumn(name = "ID_Employee"),
        inverseJoinColumns = @JoinColumn(name = "ID_Route"))
    private List<Route> route;

    public Employee(String email, String password, Boolean active, String surname, String name, String middleName, String phone, Set<Post> post) {
        this.email = email;
        this.password = password;
        this.active = active;
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.phone = phone;
        this.post = post;
    }

    public Employee() {}

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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Post> getPost() {
        return post;
    }

    public void setPost(Set<Post> post) {
        this.post = post;
    }

    public Collection<Plan> getPlan() {
        return plan;
    }

    public void setPlan(Collection<Plan> plan) {
        this.plan = plan;
    }

    public Collection<Check> getCheck() {
        return check;
    }

    public void setCheck(Collection<Check> check) {
        this.check = check;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public Collection<Register> getRegister() {
        return register;
    }

    public void setRegister(Collection<Register> register) {
        this.register = register;
    }

    public Collection<Order> getOrder() {
        return order;
    }

    public void setOrder(Collection<Order> order) {
        this.order = order;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
    public List<Route> getRoute() {
        return route;
    }
    public void setRoute(List<Route> route) {
        this.route = route;
    }
}
