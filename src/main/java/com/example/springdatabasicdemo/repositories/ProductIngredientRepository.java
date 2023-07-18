package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.models.Ingredient;
import com.example.springdatabasicdemo.models.Product;
import com.example.springdatabasicdemo.models.ProductIngredient;
import com.example.springdatabasicdemo.models.Product_Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductIngredientRepository extends JpaRepository<ProductIngredient, Integer> {
    Set<ProductIngredient> findProductIngredientById(Integer id);
    
    Set<ProductIngredient> findProductIngredientByProduct(Product product);
    
    Set<ProductIngredient> findProductIngredientByIngredient(Ingredient ingredient);

    void deleteProductIngredientById(Integer id);
    
    void deleteProductIngredientByProduct(Product product);
    
    void deleteProductIngredientByIngredient(Ingredient ingredient);
}
