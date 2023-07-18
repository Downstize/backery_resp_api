package com.example.springdatabasicdemo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "product_backery")
public class ProductBackery {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "pr_ba_id") // Исправлено на "product_id"
//    protected Integer id;
    @EmbeddedId
    private ProductBackeryKey id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId("productId")
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId("backeryId")
    @JoinColumn(name = "backery_id", insertable = false, updatable = false)
    private Backery backery;

    @Column(name = "price", length = 50, nullable = false)
    private Integer price;

    @Column(name = "amount") // Исправлено на "product_id"
    protected Integer amount;

    protected ProductBackery() {}

    public ProductBackery(ProductBackeryKey productBackeryKey, Product product, Backery backery, Integer price,Integer amount) {
        this.id = productBackeryKey;
        this.product = product;
        this.backery = backery;
        this.price = price;
        this.amount = amount;
    }

    public Product getProduct() {return product;}

    public void setProduct(Product product) {this.product = product;}

    public Backery getBackery() {return backery;}

    public void setBackery(Backery backery) {this.backery = backery;}

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }
}
