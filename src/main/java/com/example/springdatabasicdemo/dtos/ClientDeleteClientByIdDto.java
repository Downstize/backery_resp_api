package com.example.springdatabasicdemo.dtos;

public class ClientDeleteClientByIdDto {

    private Long id;

    public ClientDeleteClientByIdDto(Long id){
        this.id = id;
    }

    public ClientDeleteClientByIdDto(){}

    public Long getId(){return id;}

    public void setId(Long id){this.id = id;}
}
