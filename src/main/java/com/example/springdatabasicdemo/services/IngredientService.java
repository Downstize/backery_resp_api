package com.example.springdatabasicdemo.services;
import com.example.springdatabasicdemo.dtos.IngredientDto;
import com.example.springdatabasicdemo.dtos.ProductDto;
import com.example.springdatabasicdemo.models.Product;

import java.util.List;
import java.util.Optional;

public interface IngredientService<ID> {

    void addNewIngredientInProduct(IngredientDto ingredient, ProductDto product);
    
    IngredientDto addNewIngredient(IngredientDto ingredient);

    void deleteIngredientByName(IngredientDto ingredient);

    void deleteIngredientById(Long id);

    Optional<IngredientDto> findIngredient(Long id);

    List<IngredientDto> getAllIngredients();

    List<IngredientDto> findIngredientByProducts(ProductDto products);

    void changeIngredientName(IngredientDto ingredient, String name);

    void changeIngredientAmount(IngredientDto ingredient, Integer amount);

}
