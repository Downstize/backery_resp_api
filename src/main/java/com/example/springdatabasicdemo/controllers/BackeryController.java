package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.*;

import com.example.springdatabasicdemo.models.Backery;
import com.example.springdatabasicdemo.services.BackeryService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class BackeryController {

    private final BackeryService<Integer> backeryService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public BackeryController(BackeryService<Integer> backeryService) {
        this.backeryService = backeryService;
    }

    @GetMapping("/backeries")
    public List<BackeryDto> getAllBakeries() {
        return backeryService.findAll();
    }

    @PostMapping("/open/backery")
    public void openBakery(@RequestBody BackeryDto newBakery) {
        backeryService.openBackery(newBakery);
    }

    @GetMapping("/backery/id")
    public BackeryDto getBakeryById(@RequestBody BackeryFindIdDto backeryFindIdDto) {
        return backeryService.findBackery(backeryFindIdDto.getId()).orElseThrow(() -> new BackeryNotFoundException(backeryFindIdDto.getId()));
    }

    @DeleteMapping("/close/backery")
    public void closeBakeryById(@RequestBody BackeryFindIdDto backeryFindIdDto) {
        backeryService.closeBackery(backeryFindIdDto.getId());
    }

    @PutMapping("/backery/change/name")
    public void changeBakeryName(@RequestBody BackeryChangeNameDto backeryChangeNameDto) {
        Optional<BackeryDto> bakeryDtoOptional = backeryService.findBackery(backeryChangeNameDto.getId());

        BackeryDto bakeryDto = bakeryDtoOptional.orElseThrow(() -> new BackeryNotFoundException(backeryChangeNameDto.getId()));

        backeryService.changeBackeryName(bakeryDto, backeryChangeNameDto.getName());
    }



    @GetMapping("/backeries/find/by-product-name")
    public List<BackeryDto> findBakeriesByProductName(@RequestBody ProductFindByNameDto productName) {
        List<Backery> backeries = backeryService.findBakeriesByProductName(productName.getName());
        List<BackeryDto> backeriesList= new ArrayList<>();
        for (int i = 0; i < backeries.size(); i++) {
            backeriesList.add(modelMapper.map(backeries.get(i), BackeryDto.class));
        }
        return backeriesList;
    }


    @PutMapping("/backery/change/address/")
    public void changeBakeryAddress(@RequestBody BackeryChangeAddressDto backeryChangeAddressDto) {
        Optional<BackeryDto> bakeryDtoOptional = backeryService.findBackery(backeryChangeAddressDto.getId());

        BackeryDto bakeryDto = bakeryDtoOptional.orElseThrow(() -> new BackeryNotFoundException(backeryChangeAddressDto.getId()));

        backeryService.changeBackeryAddress(bakeryDto, backeryChangeAddressDto.getAddress());
    }



    @PutMapping("/backery/change/phone/")
    public void changeBakeryPhone(@RequestBody BackeryChangePhoneDto backeryChangePhoneDto) {
        Optional<BackeryDto> bakeryDtoOptional = backeryService.findBackery(backeryChangePhoneDto.getId());

        BackeryDto bakeryDto = bakeryDtoOptional.orElseThrow(() -> new BackeryNotFoundException(backeryChangePhoneDto.getId()));

        backeryService.changeBackeryPhone(bakeryDto, backeryChangePhoneDto.getPhoneNumber());
    }



    @GetMapping("/backery/products/{id}")
    public Set<ProductDto> getBakeryProducts(@PathVariable Long id) {
        Optional<BackeryDto> bakeryDtoOptional = backeryService.findBackery(id);

        BackeryDto bakeryDto = bakeryDtoOptional.orElseThrow(() -> new BackeryNotFoundException(id));

        return backeryService.getAllBackeryProducts(bakeryDto);
    }



    @GetMapping("/backeries/address/{address}")
    public Optional<BackeryDto> getBakeriesByAddress(@RequestBody BackeryChangeAddressDto backeryChangeAddressDto) {
        Optional<BackeryDto> bakeryDtoOptional = backeryService.findBackeryByAddress(backeryChangeAddressDto.getAddress());
        BackeryDto bakeryDto = bakeryDtoOptional.orElseThrow(() -> new BackeryNotFoundAddressException(backeryChangeAddressDto.getAddress()));
        return  backeryService.findBackeryByAddress(bakeryDto.getAddress());
    }

    @GetMapping("/backeries/name/{name}")
    public BackeryDto getBakeriesByName(@RequestBody BackeryChangeNameDto backeryChangeNameDto) {
        return backeryService.findByName(backeryChangeNameDto.getName());
    }

    @DeleteMapping("/backeries/address/{address}")
    public void closeBakeryByAddress(@PathVariable String address) {
        Optional<BackeryDto> bakeryDtoOptional = backeryService.findBackeryByAddress(address);

        BackeryDto bakeryDto = bakeryDtoOptional.orElseThrow(() -> new BackeryNotFoundAddressException("Address: " + address));

        backeryService.closeBackery(bakeryDto.getId());
    }


    @DeleteMapping("/backeries/name/{name}")
    public void closeBakeryByName(@RequestBody BackeryChangeNameDto backeryChangeNameDto) {
        BackeryDto bakeryDto = backeryService.findByName(backeryChangeNameDto.getName());

        backeryService.closeBackeryByName(bakeryDto);
    }


}
