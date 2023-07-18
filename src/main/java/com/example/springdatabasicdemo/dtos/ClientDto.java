package com.example.springdatabasicdemo.dtos;

public class ClientDto {


    private Integer id;
    private String name;
    private String surname;
//     private OrderDto orders;

    public ClientDto(Integer id, String name, String surname){
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public ClientDto(){}

    public Integer getId(){return id;}

    public void setId(Integer id){this.id = id;}

    public String getClient_name(){return name;}

    public void setClient_name(String name){this.name = name;}

    public String getClient_surname(){return surname;}

    public void setClient_surname(String surname){this.surname = surname;}

//     public OrderDto getClientOrders(){return orders;}
//
//     public void setClientOrders(OrderDto orders){this.orders = orders;}
}
