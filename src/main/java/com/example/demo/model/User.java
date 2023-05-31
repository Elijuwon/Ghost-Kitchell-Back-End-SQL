package com.example.demo.model;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String username;

    @Column
    String password;

    @Column
    String email;

    @Column
    String phone;

    @Column
    String firstName;

    @Column
    String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany
    private List<CustomerOrder> order = new ArrayList <CustomerOrder>();

    @OneToOne
    CustomerOrder currentOrder;

    public void setOrder (List<CustomerOrder> order) {
        this.order = order;
    }

    public CustomerOrder getCurrentOrder () {
        return currentOrder;
    }

    public void setCurrentOrder (CustomerOrder currentOrder) {
        this.currentOrder = currentOrder;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public Address getAddress () {
        return address;
    }

    public void setAddress (Address address) {
        this.address = address;
    }

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getPhone () {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public Set<Role> getRoles () {
        return roles;
    }

    public void setRoles (Set<Role> roles) {
        this.roles = roles;
    }

    public List<CustomerOrder> getOrder () {
        return order;
    }
}
