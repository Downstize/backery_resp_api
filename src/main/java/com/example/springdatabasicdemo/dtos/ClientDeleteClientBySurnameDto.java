package com.example.springdatabasicdemo.dtos;

public class ClientDeleteClientBySurnameDto {

    private String surname;

    public ClientDeleteClientBySurnameDto(String surname){
        this.surname = surname;
    }

    public ClientDeleteClientBySurnameDto(){}

    public String getSurame(){return surname;}

    public void setSurname(String surname){this.surname = surname;}
}
