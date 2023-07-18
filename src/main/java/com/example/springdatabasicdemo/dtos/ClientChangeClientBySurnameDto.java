package com.example.springdatabasicdemo.dtos;

public class ClientChangeClientBySurnameDto {

    private String surname;

       public ClientChangeClientBySurnameDto(String surname){
           this.surname = surname;
       }

       public ClientChangeClientBySurnameDto(){}

       public String getSurname(){return surname;}

       public void setSurname(String surname){this.surname = surname;}
}
