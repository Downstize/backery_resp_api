package com.example.springdatabasicdemo.dtos;

public class OrderRegistrationRequestDto {
    private ClientDto client;
    private OrderDto order;

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
    }
}

