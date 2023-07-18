package com.example.springdatabasicdemo.services.impl;
import com.example.springdatabasicdemo.dtos.ClientDto;
import com.example.springdatabasicdemo.dtos.OrderDto;
import com.example.springdatabasicdemo.dtos.IngredientDto;
import com.example.springdatabasicdemo.dtos.ProductDto;
import com.example.springdatabasicdemo.models.Backery;
import com.example.springdatabasicdemo.models.Ingredient;
import com.example.springdatabasicdemo.models.Product;
import com.example.springdatabasicdemo.models.ProductIngredient;
import com.example.springdatabasicdemo.repositories.IngredientRepository;
import com.example.springdatabasicdemo.repositories.ProductIngredientRepository;
import com.example.springdatabasicdemo.repositories.ProductRepository;
import com.example.springdatabasicdemo.services.IngredientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

@Service
public class IngredientServiceImpl implements IngredientService<Integer> {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductIngredientRepository productIngredientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public IngredientDto addNewIngredient(IngredientDto ingredient) {
        Ingredient i = modelMapper.map(ingredient,Ingredient.class);
        return modelMapper.map(ingredientRepository.save(i),IngredientDto.class);
    }

public void addNewIngredientInProduct(IngredientDto ingredientDto, ProductDto productDto) {
    Product product = modelMapper.map(productDto, Product.class);
    Ingredient ingredient = modelMapper.map(ingredientDto, Ingredient.class);

    ProductIngredient productIngredient = new ProductIngredient(product, ingredient);
    product.getProductIngredient().add(productIngredient);

    if (ingredient.getIngredientId() == 0) {
        ingredient = ingredientRepository.save(ingredient);
    }

    Product savedProduct = productRepository.save(product);
    ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);
}

    @Override
    public void deleteIngredientByName(IngredientDto ingredient) {
        ingredientRepository.deleteIngredientByName(ingredient.getName());
    }

    @Override
    public void deleteIngredientById(Long integer) {
        ingredientRepository.deleteIngredientById(integer);
    }

    @Override
    public Optional<IngredientDto> findIngredient(Long integer) {
       return Optional.ofNullable(modelMapper.map(ingredientRepository.findIngredientById(integer), IngredientDto.class));
    }

    @Override
    public List<IngredientDto> getAllIngredients() {
       return ingredientRepository.findAll().stream().map((i) -> modelMapper.map(i, IngredientDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<IngredientDto> findIngredientByProducts(ProductDto products) {
        List<Ingredient> ingredient = ingredientRepository.findIngredientById(products.getId());
        return ingredient.stream().map(ingredient1 -> modelMapper.map(ingredient, IngredientDto.class)).collect(Collectors.toList());
    }

    @Override
    public void changeIngredientName(IngredientDto ingredient, String name) {
        Ingredient i = modelMapper.map(ingredient.getName(), Ingredient.class);
        i.setName(name);
        ingredientRepository.save(i);
    }

    @Override
    public void changeIngredientAmount(IngredientDto ingredient, Integer amount) {
        Ingredient i = modelMapper.map(ingredient.getAmount(), Ingredient.class);
        i.setAmount(amount);
        ingredientRepository.save(i);
    }

}
