package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductBackeryRepository extends JpaRepository<ProductBackery, Integer> {

    List<ProductBackery> findProductBackeriesById(ProductBackeryKey id);

    List<ProductBackery> findProductBackeriesByBackery(Backery backery);

    List<ProductBackery> findProductBackeriesByProduct(Product product);
    
    void deleteProductBackeriesByBackery(Backery backery);
    
    void deleteProductBackeriesByProduct(Product product);
    
    void deleteProductBackeriesById(ProductBackeryKey id);

    ProductBackery findByProductIdAndBackeryId(Long id, Long id1);

    ProductBackery findByProductNameAndBackeryId(String productName, Long id);
}
