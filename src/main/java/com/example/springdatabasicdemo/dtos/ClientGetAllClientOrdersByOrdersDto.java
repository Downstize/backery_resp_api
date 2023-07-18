package com.example.springdatabasicdemo.dtos;

public class ClientGetAllClientOrdersByOrdersDto {

    private OrderDto orders;

        public ClientGetAllClientOrdersByOrdersDto(OrderDto orders){
            this.orders = orders;
        }

        public ClientGetAllClientOrdersByOrdersDto(){}

        public OrderDto getClientOrders(){return orders;}

        public void setClientOrders(OrderDto orders){this.orders = orders;}

}
