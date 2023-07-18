package com.example.springdatabasicdemo.dtos;

public class IngredientChangeIngredientNameDto {

    private String name;
    private Long id;

    public IngredientChangeIngredientNameDto(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public IngredientChangeIngredientNameDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id){this.id = id;}

        public String getName(){return name;}

        public void setName(String name){this.name = name;}

}
