package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;

    @OneToMany(cascade = CascadeType.ALL)
    List<MenuItem> menu = new ArrayList <MenuItem>();

    @OneToOne
    User owner;

    @OneToOne(cascade = CascadeType.ALL)
    Address address;

    String phone;

    String name;

    public Long getID () {
        return ID;
    }

    public void setID (Long ID) {
        this.ID = ID;
    }

    public List<MenuItem> getMenu () {
        return menu;
    }

    public void setMenu (List<MenuItem> menu) {
        this.menu = menu;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public User getOwner () {
        return owner;
    }

    public void setOwner (User owners) {
        this.owner = owners;
    }

    public Address getAddress () {
        return address;
    }

    public void setAddress (Address address) {
        this.address = address;
    }

    public String getPhone () {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }
}
