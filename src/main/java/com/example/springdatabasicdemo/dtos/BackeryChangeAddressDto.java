package com.example.springdatabasicdemo.dtos;
import jakarta.transaction.Transactional;

public class BackeryChangeAddressDto {

    private Long id;
    private String address;
    public BackeryChangeAddressDto(Long id, String address) {
        this.id = id;
        this.address = address;
    }

    public BackeryChangeAddressDto() {}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

}
