package com.example.springdatabasicdemo.dtos;
import jakarta.transaction.Transactional;

public class BackeryDto {
    
    private Long id;
    private String name;
    private String address;
    private String phone_number;
    
    public BackeryDto(Long id, String name, String address, String phone_number) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
    }
    
    public BackeryDto() {}

    @Transactional
    public String getAddress() {
        return address;
    }
    
    @Transactional
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

}
