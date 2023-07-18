package com.example.springdatabasicdemo.dtos;

public class IngredientGetIngredientByIdDto {

    private Long id;

        public IngredientGetIngredientByIdDto(Long id){
            this.id = id;
        }

        public IngredientGetIngredientByIdDto(){}

        public Long getId(){return id;}

        public void setId(Long id){this.id = id;}
}
