package com.example.springdatabasicdemo.dtos;

public class ClientDeleteClientByNameDto {

    private String name;

    public ClientDeleteClientByNameDto(String name){
        this.name = name;
    }

    public ClientDeleteClientByNameDto(){}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}
}
