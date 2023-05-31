package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "menuItem")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String menuItem;

    @Column
    BigDecimal price;

    @Column
    String description;

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getMenuItem () {
        return menuItem;
    }

    public void setMenuItem (String menuItem) {
        this.menuItem = menuItem;
    }

    public BigDecimal getPrice () {
        return price;
    }

    public void setPrice (BigDecimal price) {
        this.price = price;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }
}
