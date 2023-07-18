package com.example.springdatabasicdemo.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    protected Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "amount", length = 50, nullable = true)
    private Integer amount;

    @OneToMany(mappedBy = "ingredient")
    Set<ProductIngredient> productIngredients;
    
    public Ingredient(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    protected Ingredient(){
    }

    public Long getIngredientId(){return id;}

    public void setIngredientId(Long id){this.id = id;}

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

}
