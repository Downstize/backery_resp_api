package com.example.springdatabasicdemo.dtos;
import com.example.springdatabasicdemo.models.Client;
import com.example.springdatabasicdemo.models.Product;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private Integer id;
    private LocalDateTime time;
    private String status;
    private ClientDto client;
    private List<ProductDto> products;

    public OrderDto(Integer id, LocalDateTime time, String status, ClientDto client){
        this.id = id;
        this.time = time;
        this.status = status;
        this.client = client;
        this.products = new ArrayList<>();
    }

    public OrderDto(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getOrderTime() {
        return time;
    }

    public void setOrderTime(LocalDateTime order_time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

}
