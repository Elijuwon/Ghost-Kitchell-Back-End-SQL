package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;

    @OneToMany(cascade = CascadeType.ALL)
    List<MenuItem> orderitems  = new ArrayList<>();

    @Column
    BigDecimal grandTotal;

    @Column
    Double subTotal;

    public Long getID () {
        return ID;
    }

    public void setID (Long ID) {
        this.ID = ID;
    }

    public List<MenuItem> getOrderitems () {
        return orderitems;
    }

    public void setOrderitems (List<MenuItem> orderitems) {
        this.orderitems = orderitems;
    }

    public BigDecimal getGrandTotal () {
        return grandTotal;
    }

    public void setGrandTotal (BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Double getSubTotal () {
        return subTotal;
    }

    public void setSubTotal (Double subTotal) {
        this.subTotal = subTotal;
    }
}
