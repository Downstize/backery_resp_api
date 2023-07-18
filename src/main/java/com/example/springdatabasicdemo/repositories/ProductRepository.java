package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.dtos.ProductDto;
import com.example.springdatabasicdemo.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductByName(String name);

    //List<Product> findProductByProductIngredient(Set<ProductIngredient> productIngredients);

    //List<Product> findProductsByProductBackeries(Set<ProductBackery> productBackeries);
    
    //List<Product> findProductsByPrice(Integer price);

    //List<Product> findProductsByBackery(Backery backery);
    
    Optional<Product> findProductById(Long id);

    @Modifying
    @Query("UPDATE ProductBackery pb " +
            "SET pb.amount = pb.amount + :amount " +
            "WHERE pb.product = :product AND pb.backery = :backery")
    void updateProductBackeryAmount(Product product, Backery backery, Integer amount);

    @Query("SELECT DISTINCT p FROM Product p JOIN FETCH p.productIngredients")
    List<Product> findAllWithIngredients();

    @Query("SELECT o FROM Order o JOIN o.orderProducts i WHERE i.product.name = :productName")
    List<Order> findOrdersByProductName(@Param("productName") String productName);

    @Query("SELECT p FROM Product p JOIN p.productBackeries pb WHERE pb.backery.name = :backeryName AND pb.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findProductsByBackeryAndPriceRange(@Param("backeryName") String backeryName, @Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

    //void deleteProductById(Integer id);
    
    void deleteProductByName(String name);

    //void deleteProductByProductIngredient(Set<ProductIngredient> productIngredient);

    //void deleteProductByProductBackeries(Set<ProductBackery> productBackeries);
    
    void deleteProductByDescription(String description);
    
    //void deleteProductByPrice(Integer price);
}
