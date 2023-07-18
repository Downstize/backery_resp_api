package com.example.springdatabasicdemo.models;
import jakarta.persistence.*;

@Entity
@Table(name = "product_ingredient")
public class ProductIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pr_in_id") // Исправлено на "product_id"
    protected Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredient_id")
    Ingredient ingredient;

    protected ProductIngredient() {}

    public ProductIngredient(Product product, Ingredient ingredient) {
        this.product = product;
        this.ingredient = ingredient;
        product.getProductIngredient().add(this);
        ingredient.getProductIngredient().add(this);
    }

    public Product getProduct() {return product;}

    public void setProduct(Product product) {this.product = product;}

    public Ingredient getIngredient() {return ingredient;}

    public void setIngredient(Ingredient ingredient) {this.ingredient = ingredient;}
}
