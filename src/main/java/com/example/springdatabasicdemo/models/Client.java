package com.example.springdatabasicdemo.models;
import jakarta.persistence.Column;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false)
    protected Integer id;

    @Column(name = "client_name", length = 100)
    private String name;

    @Column(name = "client_surname", length = 100)
    private String surname;

    @Column(name = "client_address", length = 100)
    private String address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Order> orders;

    public Client(String name, String surname, String address){
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    protected Client(){}

    public Integer getClientId(){return id;}

    public void setClientId(){this.id = id;}

    public String getClient_name() {return name;}

    public void setClient_name(String name){
        this.name = name;
    }

    public String getClient_surname(){return surname;}

    public void setClient_surname(String surname){this.surname = surname;}

    public String getClient_address(){return address;}

    public void setClient_address(String address){this.address = address;}

    public List<Order> getOrders() {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
