package com.example.springdatabasicdemo.init;

import com.example.springdatabasicdemo.dtos.*;
import com.example.springdatabasicdemo.services.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private BackeryService backeryService;

    @Autowired
    private OrderService orderService;

    @Override
    public void run(String... args) throws Exception {
        seedData();
    }


    private void printAllBakeryProducts(BackeryDto bakery) {
        backeryService.getAllBackeryProducts(bakery).forEach(System.out::println);
    }

    private void printAllCLientOrders(ClientDto client, OrderDto orders) {
        clientService.getAllClientOrders(client, orders).forEach(System.out::println);
    }


//    @Transactional
    public void seedData() throws IOException {

        //Ingredient

        IngredientDto ingredient1 = new IngredientDto(0L, "Flour", 500);
        IngredientDto ingredient2 = new IngredientDto(0L, "Sugar", 200);
        IngredientDto ingredient3 = new IngredientDto(0L, "Chocolate", 300);
        IngredientDto ingredient4 = new IngredientDto(0L, "Butter", 150);
        ingredient1 = ingredientService.addNewIngredient(ingredient1);
        ingredient2 = ingredientService.addNewIngredient(ingredient2);
        ingredient3 = ingredientService.addNewIngredient(ingredient3);
        ingredient4 = ingredientService.addNewIngredient(ingredient4);

        // Bakery
        BackeryDto backery1 = new BackeryDto(0L, "Shtolle", "Perviy pereulok", "1234567890");
        BackeryDto backery2 = new BackeryDto(0L, "Bulka Khleba", "6th Pobeda Street", "9876543210");
        BackeryDto backery3 = new BackeryDto(0L, "Memories", "5th Pobeda Street", "555-1234");

        // Eagerly fetch the bakery entities
        BackeryDto savedBackery1 = backeryService.openBackery(backery1);
        BackeryDto savedBackery2 = backeryService.openBackery(backery2);
        BackeryDto savedBackery3 = backeryService.openBackery(backery3);

        // ...

        // Create products with the fetched bakeries
        // Create products with the fetched bakeries
        List<IngredientDto> ingredients = Arrays.asList(ingredient1, ingredient2);
        ProductDto product1 = new ProductDto(0L, "ProductName",  "Product Description");
        ProductDto product2 = new ProductDto(0L, "Product2Name",  "Product2 Description");
        ProductDto product3 = new ProductDto(0L, "Product3Name", "Product3 Description");

        ProductDto savedProduct1 = productService.cookProduct(product1, savedBackery1,10, 6);
        ProductDto savedProduct2 = productService.cookProduct(product2, savedBackery2, 12,10);
        ProductDto savedProduct3 = productService.cookProduct(product3, savedBackery3, 15,333);

        ingredientService.addNewIngredientInProduct(ingredient1,savedProduct1);
        ingredientService.addNewIngredientInProduct(ingredient2,savedProduct2);
        ingredientService.addNewIngredientInProduct(ingredient3,savedProduct3);

        //Client

        ClientDto client1 = new ClientDto(0, "John", "Doe");
        ClientDto client2 = new ClientDto(0, "Jane", "Smith");
        ClientDto client3 = new ClientDto(0, "Sarah", "Johnson");
        ClientDto savedClient1 = clientService.registerNewClient(client1);
        ClientDto savedClient2 = clientService.registerNewClient(client2);
        ClientDto savedClient3 = clientService.registerNewClient(client3);

         //Order
        List<ProductDto> products = Arrays.asList(savedProduct1, savedProduct2, savedProduct3);

        List<ProductDto> products2 = Collections.singletonList(savedProduct1);
        OrderDto order1 = new OrderDto(0, LocalDateTime.now(), "In Progress", client1);
        OrderDto order2 = new OrderDto(0, LocalDateTime.now(), "In Progress", client2);
        OrderDto order3 = new OrderDto(0, LocalDateTime.now(), "In Progress", client3);

        OrderDto savedOrder1 = orderService.registerNewOrder(savedClient1, order1);
        OrderDto savedOrder2 = orderService.registerNewOrder(savedClient2, order2);
        OrderDto saveOrderProducts1 = orderService.addProductsInNewOrder(savedOrder1, savedProduct1);
        OrderDto saveOrderProducts2 = orderService.addProductsInNewOrder(savedOrder2, savedProduct2);

        printAllCLientOrders(client1, order1);

    }
}
