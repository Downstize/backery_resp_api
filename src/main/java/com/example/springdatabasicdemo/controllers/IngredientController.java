package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.IngredientDeleteIngredientByIdDto;
import com.example.springdatabasicdemo.dtos.IngredientDeleteIngredientByNameDto;
import com.example.springdatabasicdemo.dtos.IngredientGetIngredientByIdDto;
import com.example.springdatabasicdemo.dtos.IngredientGetIngredientsByProductDto;
import com.example.springdatabasicdemo.dtos.IngredientChangeIngredientNameDto;
import com.example.springdatabasicdemo.dtos.IngredientChangeIngredientAmountDto;
import com.example.springdatabasicdemo.dtos.IngredientDto;
import com.example.springdatabasicdemo.dtos.ProductDto;
import com.example.springdatabasicdemo.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class IngredientController {

    private final IngredientService<Integer> ingredientService;

    @Autowired
    public IngredientController(IngredientService<Integer> ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredients")
    public List<IngredientDto> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @PostMapping("/add/ingredient")
    public IngredientDto addNewIngredient(@RequestBody IngredientDto newIngredient) {
        return ingredientService.addNewIngredient(newIngredient);
    }

    @DeleteMapping("/delete/ingredient/id")
    public void deleteIngredientById(@RequestBody IngredientDeleteIngredientByIdDto ingredientDelById) {
        ingredientService.deleteIngredientById(ingredientDelById.getId());
    }

    @DeleteMapping("/delete/ingredient/name")
    public void deleteIngredientByName(@RequestBody IngredientDeleteIngredientByNameDto ingredientDelByName) {
        IngredientDto ingredient = new IngredientDto();
        ingredient.setName(ingredientDelByName.getName());
        ingredientService.deleteIngredientByName(ingredient);
    }


    @GetMapping("/ingredient/id")
    public Optional<IngredientDto> getIngredientById(@RequestBody IngredientGetIngredientByIdDto ingredientGetById) {
        return ingredientService.findIngredient(ingredientGetById.getId());
    }

    @GetMapping("/ingredients/product/id")
    public List<IngredientDto> getIngredientsByProduct(@RequestBody IngredientGetIngredientsByProductDto ingredientsByProduct) {
        return ingredientService.findIngredientByProducts(ingredientsByProduct.getIngredientsOfProduct());
    }

    @PutMapping("/change/ingredient/name")
    public void changeIngredientName(@RequestBody IngredientChangeIngredientNameDto ingredientChangeName) {
        IngredientDto ingredientDto = ingredientService.findIngredient(ingredientChangeName.getId())
                .orElseThrow(() -> new IngredientNotFoundException(Math.toIntExact(ingredientChangeName.getId())));
        ingredientService.changeIngredientName(ingredientDto, ingredientChangeName.getName());
    }

    @PutMapping("/change/ingredient/amount")
    public void changeIngredientAmount(@RequestBody IngredientChangeIngredientAmountDto ingredientChangeAmount) {
        IngredientDto ingredientDto = ingredientService.findIngredient(ingredientChangeAmount.getId())
                .orElseThrow(() -> new IngredientNotFoundException(Math.toIntExact(ingredientChangeAmount.getId())));
        ingredientService.changeIngredientAmount(ingredientDto, ingredientChangeAmount.getAmount());
    }
}
