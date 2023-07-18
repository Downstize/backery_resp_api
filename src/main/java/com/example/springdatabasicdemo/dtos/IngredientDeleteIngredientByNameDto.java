package com.example.springdatabasicdemo.dtos;

public class IngredientDeleteIngredientByNameDto {

    private String name;

        public IngredientDeleteIngredientByNameDto(String name){
            this.name = name;
        }

        public IngredientDeleteIngredientByNameDto(){}

        public String getName(){return name;}

        public void setName(String name){this.name = name;}
}
