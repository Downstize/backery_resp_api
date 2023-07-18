package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.BackeryDto;
import com.example.springdatabasicdemo.dtos.BackeryDto;
import com.example.springdatabasicdemo.dtos.ProductDto;
import com.example.springdatabasicdemo.models.Backery;
import com.example.springdatabasicdemo.models.Product;
import com.example.springdatabasicdemo.models.ProductBackery;

import com.example.springdatabasicdemo.repositories.BackeryRepository;
import com.example.springdatabasicdemo.repositories.ProductRepository;

import com.example.springdatabasicdemo.services.BackeryService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

@Service
public class BackeryServiceImpl implements BackeryService<Integer> {

    @Autowired
    private BackeryRepository backeryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

   @Override
   public BackeryDto openBackery(BackeryDto backeryDto) {
       Backery existingBackery = backeryRepository.findBackeryByName(backeryDto.getName());
       if (existingBackery != null) {
           throw new IllegalArgumentException("Backery with the provided name already exists.");
       }

       Backery newBackery = new Backery(0L, backeryDto.getName(), backeryDto.getAddress(), backeryDto.getPhoneNumber());
       Backery savedBackery = backeryRepository.save(newBackery);

       return modelMapper.map(savedBackery, BackeryDto.class);
   }

    @Transactional
    @Override
    public void closeBackeryByAddress(BackeryDto bakery) {
        backeryRepository.deleteBackeryByAddress(bakery.getAddress());
    }

    @Transactional
    @Override
    public void closeBackeryByName(BackeryDto bakery) {
        backeryRepository.deleteBackeryByAddress(bakery.getName());
    }

    @Transactional
    @Override
    public void closeBackery(Long id) {
        backeryRepository.deleteBackeriesById(id);
    }

    @Transactional
    @Override
    public void changeBackeryName(BackeryDto bakery, String name) {
        Backery b = backeryRepository.findBackeriesById(bakery.getId());
        b.setName(name);
        backeryRepository.save(b);
    }

    @Transactional
    @Override
    public void changeBackeryAddress(BackeryDto bakery, String address) {
        Backery b = backeryRepository.findBackeriesById(bakery.getId());
        b.setAddress(address);
        backeryRepository.save(b);
    }

    @Transactional
    @Override
    public void changeBackeryPhone(BackeryDto bakery, String phone_number) {
        Backery b = backeryRepository.findBackeriesById(bakery.getId());
        b.setPhoneNumber(phone_number);
        backeryRepository.save(b);
    }

    @Transactional
    @Override
    public Set<ProductDto> getAllBackeryProducts(BackeryDto backeryDto) {
        Backery backery = modelMapper.map(backeryDto, Backery.class);
        Set<ProductBackery> productBackeries = backery.getProductBackeries();
        Set<ProductDto> products = new HashSet<>();
        for (ProductBackery productBackery : productBackeries) {
            ProductDto productDto = modelMapper.map(productBackery.getProduct(), ProductDto.class);
            products.add(productDto);
        }
        return products;
    }



    @Override
    public List<Backery> findBakeriesByProductName(String productName) {
        return backeryRepository.findBakeriesByProductId(productName);
    }


    @Transactional
    @Override
    public Optional<BackeryDto> findBackery(Long id) {
        Backery backery = backeryRepository.findBackeriesById(id);
        return Optional.ofNullable(modelMapper.map(backery, BackeryDto.class));
    }



    @Transactional
    @Override
    public List<BackeryDto> findAll() {
        return backeryRepository.findAll().stream().map((b) -> modelMapper.map(b, BackeryDto.class)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Optional<BackeryDto> findBackeryByAddress(String address) {
        Backery backery = backeryRepository.findBackeriesByAddress(address);
        return Optional.ofNullable(modelMapper.map(backery, BackeryDto.class));
    }


    @Transactional
    @Override
    public BackeryDto findByName(String name) {
        Backery backery = backeryRepository.findBackeryByName(name);
        BackeryDto backeryDto = modelMapper.map(backery, BackeryDto.class);


        return backeryDto;
    }


}
