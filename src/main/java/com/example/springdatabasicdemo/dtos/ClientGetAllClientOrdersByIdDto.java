package com.example.springdatabasicdemo.dtos;

public class ClientGetAllClientOrdersByIdDto {

    private Long id;

    public ClientGetAllClientOrdersByIdDto(Long id){
        this.id = id;
    }

    public ClientGetAllClientOrdersByIdDto(){}

    public Long getId(){return id;}

    public void setId(Long id){this.id = id;}
}
