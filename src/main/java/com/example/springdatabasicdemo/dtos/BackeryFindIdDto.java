package com.example.springdatabasicdemo.dtos;

public class BackeryFindIdDto {

    private Long id;
    public BackeryFindIdDto(Long id) {
        this.id = id;
    }

    public BackeryFindIdDto() {}

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }
}
