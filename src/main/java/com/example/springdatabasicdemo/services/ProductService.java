package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.*;
import com.example.springdatabasicdemo.models.Backery;
import com.example.springdatabasicdemo.models.Ingredient;
import com.example.springdatabasicdemo.models.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductService<ID> {

    public ProductDto cookProduct(ProductDto productDto, BackeryDto backeryDto, Integer amount, Integer price);

    void saleProductInBackery(ProductDto productDto, BackeryDto backeryDto, int soldAmount);

//    void saleProductByPrice(ProductDto product);
//
//    void saleProductByName(ProductDto product);

//    void saleProduct(ID id);

//    void changeProductRecipe(ProductDto product, IngredientDto ingredient1, IngredientDto ingredient2);

   void changeProductPriceInBackery(ProductDto product, BackeryDto backery,Integer price);

//    void changeProductRecipe(ProductDto product, Integer price);

    void changeProductName(ProductDto product, String name);
    void changeProductDescription(ProductDto productDto, String description);

    void deleteIngredientInProduct(ProductDto product, IngredientDto ingredient);

    void addIngredientInProduct(ProductDto product, IngredientDto ingredient);

//    void makeStocksInBackery(ProductDto product, BackeryDto backery, Integer percent);

    public void makeStocksOnProductInBackery(String productName, BackeryDto backeryDto, double percentage);

//    List<BakeryDto> getAllBakeriesByProduct(ProductDto product);

//    Optional<ProductDto> findProduct(Long id);

    Optional<ProductDto> findProduct(Integer id);
    
    Optional<ProductDto> findProductById(Long id);

    List<ProductDto> findAll();

    ProductDto  cookProduct2(ProductDto productDto, BackeryDto backeryDto, Integer amount, Integer price);

    Integer findProductPriceByBackery(ProductDto productDto, BackeryDto backeryDto);

//    List<ProductDto> findByPriceInBackery(Integer price);

    List<Product> findProductsByBackeryAndPriceRange(BackeryDto backeryDto, double minPrice, double maxPrice);

//    List<ProductDto> findByDescription(String description);
}


