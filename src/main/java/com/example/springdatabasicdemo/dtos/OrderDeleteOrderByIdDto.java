package com.example.springdatabasicdemo.dtos;

public class OrderDeleteOrderByIdDto {

     private Long id;

     public OrderDeleteOrderByIdDto(Long id){
            this.id = id;
     }

     public OrderDeleteOrderByIdDto(){}

     public Long getId(){return id;}

     public void setId(Long id){this.id = id;}
}
