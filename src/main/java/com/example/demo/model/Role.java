package com.example.demo.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column
    private RoleName name;

    public Long getID () {
        return ID;
    }

    public void setID (Long ID) {
        this.ID = ID;
    }

    public RoleName getName () {
        return name;
    }

    public void setName (RoleName name) {
        this.name = name;
    }
}
