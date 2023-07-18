package com.example.springdatabasicdemo.dtos;

public class ClientFindClientByIdDto {

    private Long id;

    public ClientFindClientByIdDto(Long id){
        this.id = id;
    }

    public ClientFindClientByIdDto(){}

    public Long getId(){return id;}

    public void setId(Long id){this.id = id;}
}
