package com.example.springdatabasicdemo.dtos;
import jakarta.transaction.Transactional;

public class BackeryChangePhoneDto {

    private Long id;
    private String phone_number;

    public BackeryChangePhoneDto(Long id, String phone_number) {
        this.id = id;
        this.phone_number = phone_number;
    }

    public BackeryChangePhoneDto() {}

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

}
