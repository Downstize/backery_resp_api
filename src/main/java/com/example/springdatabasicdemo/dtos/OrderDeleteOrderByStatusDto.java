package com.example.springdatabasicdemo.dtos;

public class OrderDeleteOrderByStatusDto {

    private OrderDto order;

    public OrderDeleteOrderByStatusDto(OrderDto order){
                this.order = order;
    }

    public OrderDeleteOrderByStatusDto(){}

   public OrderDto getOrder() {
           return order;
       }

       public void setOrder(OrderDto order) {
           this.order = order;
       }
}
