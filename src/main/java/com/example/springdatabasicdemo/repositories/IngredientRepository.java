package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.models.Ingredient;
import com.example.springdatabasicdemo.models.Product;
import com.example.springdatabasicdemo.models.ProductIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.List;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    //List<Ingredient> findIngredientByProductIngredient(Set<ProductIngredient> productIngredients);

    @Query("SELECT i FROM Ingredient i JOIN FETCH i.productIngredients WHERE i.id = :id")
    Optional<Ingredient> findByIdWithProducts(@Param("id") Integer id);

    List<Ingredient> findIngredientByAmount(Integer amount);
    
    Ingredient findIngredientByName(String name);
    
    List<Ingredient> findIngredientById(Long id);
    
    void deleteIngredientById(Long id);
    
    //void deleteIngredientByProductIngredient(Set<ProductIngredient> productIngredients);
    
    void deleteIngredientByAmount(Integer amount);
    
    void deleteIngredientByName(String name);
}
