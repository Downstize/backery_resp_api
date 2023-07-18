package com.example.springdatabasicdemo.models;
import jakarta.persistence.*;

@Entity
@Table(name = "product_order")
public class Product_Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pr_or_id")
    protected Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    Order order;

    protected Product_Order() {}

    public Product_Order(Product product, Order order) {
        this.product = product;
        this.order = order;
    }

    public Integer getProductOrderId(){return id;}

    public void setProductOrderId(Integer id){this.id = id;}

    public Product getProduct() {return product;}

    public void setProduct(Product product) {this.product = product;}

    public Order getOrder() {return order;}

    public void setOrder(Order order) {this.order = order;}
}
