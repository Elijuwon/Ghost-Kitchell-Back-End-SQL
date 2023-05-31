package com.example.demo.model;

import javax.persistence.*;


@Entity
@Table(name = "address")

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String street;

    @Column
    String state;

    @Column
    String zip;

    @Column
    String city;

    public Long getId () {
        return id;
    }

    public Address (String street, String state,String zip, String city) {
        this.state = state;
        this.street = street;
        this.city = city;
        this.zip=zip;
    }

    public Address () {

    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getStreet () {
        return street;
    }

    public void setStreet (String street) {
        this.street = street;
    }

    public String getState () {
        return state;
    }

    public void setState (String state) {
        this.state = state;
    }

    public String getZip () {
        return zip;
    }

    public void setZip (String zip) {
        this.zip = zip;
    }

    public String getCity () {
        return city;
    }

    public void setCity (String city) {
        this.city = city;
    }
}
