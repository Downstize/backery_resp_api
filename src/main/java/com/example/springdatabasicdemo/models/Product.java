package com.example.springdatabasicdemo.models;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    protected Long id;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    Set<ProductBackery> productBackeries;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    Set<ProductIngredient> productIngredients;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    Set<Product_Order> productInOrder;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "description", length = 255, nullable = false)
    private String description;

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
        this.productBackeries = new HashSet<>();
        this.productIngredients = new HashSet<>();
    }

    protected Product(){}

//    public void addProductBackery(Backery backery, Integer amount) {
//        ProductBackery productBackery = new ProductBackery(this, backery, amount);
//        productBackeries.add(productBackery);
//        backery.getProductBackeries().add(productBackery);
//    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductIngredient> getProductIngredient() {
        if (productIngredients == null) {
            productIngredients = new HashSet<>();
        }
        return productIngredients;
    }

    public void setProductIngredient(Set<ProductIngredient> productIngredients) {
        this.productIngredients = productIngredients;
    }

    public Set<ProductBackery> getProductBackeries() {
        return productBackeries;
    }

    public void setProductBackeries(Set<ProductBackery> productBackeries) {
        this.productBackeries = productBackeries;
    }

    public Set<Product_Order> getOrders() {
        if (productInOrder == null) {
            productInOrder = new HashSet<>();
        }return productInOrder;
    }

    public void setProductInOrder(Set<Product_Order> productInOrder) {
        this.productInOrder = productInOrder;
    }

}
