package com.example.springdatabasicdemo.dtos;
import jakarta.transaction.Transactional;

public class BackeryChangeNameDto {

    private Long id;
    private String name;
    public BackeryChangeNameDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public BackeryChangeNameDto() {}

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
