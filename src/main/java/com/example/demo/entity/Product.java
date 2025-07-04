package com.example.demo.entity;

import com.example.demo.validators.ValidEnufParts;
import com.example.demo.validators.ValidProductPrice;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 *
 *
 */
@Entity
@Table(name="Products")
// Custom validators: product price and minimum parts checks
@ValidProductPrice
@ValidEnufParts
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;  // Primary key

    String name;  // Product name

    @Min(value = 0, message = "Price value must be positive")
    double price;  // Product price, must be positive

    @Min(value = 0, message = "Inventory value must be positive")
    int inv;  // Inventory count, must be positive

    // Many-to-many relation with Parts (a product has many parts)
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    Set<Part> parts = new HashSet<>();

    // Getters and setters for encapsulation

    // equals and hashCode overridden for entity identity by id

    // toString returns product name for readability

    public Product() {
    }

    public Product(String name, double price, int inv) {
        this.name = name;
        this.price = price;
        this.inv = inv;
    }

    public Product(long id, String name, double price, int inv) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inv = inv;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInv() {
        return inv;
    }

    public void setInv(int inv) {
        this.inv = inv;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    public String toString(){
        return this.name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id == product.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
