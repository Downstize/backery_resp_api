package com.example.springdatabasicdemo.models;
import jakarta.transaction.Transactional;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "backery")
public class Backery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "backery_id")
    protected Long id;

    @OneToMany(mappedBy = "backery", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<ProductBackery> productBackeries;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @Column(name = "phone_number", length = 50, nullable = false)
    private String phone_number;

    public Backery(Long id, String name, String address, String phone_number) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.productBackeries = new HashSet<>();
    }

    protected Backery(){
    }

    public Set<ProductBackery> getProductBackeries() {
        if (productBackeries == null) {
            productBackeries = new HashSet<>();
        }
        return productBackeries;
    }

    public void setProductBackeries(Set<ProductBackery> productBackeries) {
        this.productBackeries = productBackeries;
    }

    @Transactional
    public String getAddress() {
        return address;
    }
    @Transactional
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

}
