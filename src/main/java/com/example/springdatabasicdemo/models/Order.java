package com.example.springdatabasicdemo.models;
import jakarta.persistence.Column;
import org.springframework.cglib.core.Local;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    protected Integer id;

    @Column(name = "order_time")
    private LocalDateTime time;

    @Column(name = "status", length = 100)
    private String status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    Set<Product_Order> orderProducts;

    public  Order (LocalDateTime order_time, String status){
        this.time = time;
        this.status = status;
    }

    protected Order (){}
    
    public Integer getId(){return id;}
    
    public void setId(Integer id){this.id = id;}

    public LocalDateTime getOrderTime() {return time;}

    public void setOrder_time(LocalDateTime order_time){
        this.time = time;
    }

    public String getStatus(){return status;}

    public void setStatus(String status){this.status = status;}

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Product_Order> getOrderProducts() {
        if (orderProducts == null) {
            orderProducts = new HashSet<>();
        }return orderProducts;
        }

    public void setProducts(Set<Product_Order> orderProducts) {
           this.orderProducts = orderProducts;
        }
}
