package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.models.Backery;
import com.example.springdatabasicdemo.models.ProductBackery;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BackeryRepository extends JpaRepository<Backery, Integer> {
    @Transactional
    Backery findBackeriesByAddress(String address);
    
    //List<Backery> findBackeriesByProductBackeries(Set<ProductBackery> productBackeries);
    @Transactional
    Backery findBackeriesById(Long id);

    @Query("SELECT b FROM Backery b JOIN b.productBackeries pb WHERE pb.product.name = :productName")
    List<Backery> findBakeriesByProductId(@Param("productName") String productName);
    @Transactional
    Backery findBackeryByName(String name);
//    @Transactional
//    List<Bakery> findBackeryByProducts(List<Product> products);
    @Transactional
    void deleteBackeriesById(Long id);
    @Transactional
    void deleteBackeryByAddress(String address);
    
    //void deleteBackeryByProductBackeries(Set<ProductBackery> productBackeries);
    @Transactional
    void deleteBackeriesByName(String name);
}
