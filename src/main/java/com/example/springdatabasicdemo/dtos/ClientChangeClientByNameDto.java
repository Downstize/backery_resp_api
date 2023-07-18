package com.example.springdatabasicdemo.dtos;

public class ClientChangeClientByNameDto {

   private String name;

       public ClientChangeClientByNameDto(String name){
           this.name = name;
       }

       public ClientChangeClientByNameDto(){}

       public String getName(){return name;}

       public void setName(String name){this.name = name;}
}
