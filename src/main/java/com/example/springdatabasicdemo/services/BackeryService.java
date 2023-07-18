package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.BackeryDto;
import com.example.springdatabasicdemo.dtos.ProductDto;
import com.example.springdatabasicdemo.models.Backery;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BackeryService<ID> {

    @Transactional
    BackeryDto openBackery(BackeryDto bakery);
    @Transactional
    void closeBackeryByAddress(BackeryDto bakery);
    @Transactional
    void closeBackeryByName(BackeryDto bakery);
    @Transactional
    void closeBackery(Long id);
    @Transactional
    void changeBackeryName(BackeryDto bakery, String name);
    @Transactional
    void changeBackeryAddress(BackeryDto bakery, String address);
    @Transactional
    void changeBackeryPhone(BackeryDto bakery, String phone_number);
    @Transactional
    Set<ProductDto> getAllBackeryProducts(BackeryDto bakery);
    @Transactional
    List<Backery> findBakeriesByProductName(String productName);
    @Transactional
    Optional<BackeryDto> findBackery(Long id);
    @Transactional
    List<BackeryDto> findAll();
    @Transactional
    BackeryDto findByName(String name);
    @Transactional
    Optional<BackeryDto> findBackeryByAddress(String address);
}


