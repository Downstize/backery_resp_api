package com.example.springdatabasicdemo.controllers;


import com.example.springdatabasicdemo.dtos.*;

import com.example.springdatabasicdemo.models.Backery;
import com.example.springdatabasicdemo.models.Product;
import com.example.springdatabasicdemo.models.ProductBackery;
import com.example.springdatabasicdemo.models.ProductBackeryKey;
import com.example.springdatabasicdemo.services.BackeryService;
import com.example.springdatabasicdemo.services.IngredientService;
import com.example.springdatabasicdemo.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductService<Integer> productService;

    private final IngredientService<Integer> ingredientService;

    private final BackeryService<Integer> backeryService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService<Integer> productService, IngredientService<Integer> ingredientService, BackeryService<Integer> backeryService) {
        this.productService = productService;
        this.ingredientService = ingredientService;
        this.backeryService = backeryService;
    }

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping("/cookProduct")
    public ProductDto cookProduct(@RequestBody CookProductDto cookProductDto) {

        Optional<BackeryDto> backeryOptional = backeryService.findBackery(cookProductDto.getBackeryId());
        BackeryDto backery = backeryOptional.orElseThrow(() -> new BackeryNotFoundException(cookProductDto.getBackeryId()));

        return productService.cookProduct2(cookProductDto.getProduct(), backery, cookProductDto.getAmount(), cookProductDto.getPrice());
    }








    @DeleteMapping("/products/id/delete")
    public void saleProductInBackery(@RequestBody SaleProductInBackeryDto saleProductInBackeryDto) {
        ProductDto product = modelMapper.map(saleProductInBackeryDto, ProductDto.class);
        Optional<BackeryDto> backeryDtoOptional = backeryService.findBackery(saleProductInBackeryDto.getBackeryId());
        BackeryDto backeryDto = backeryDtoOptional.orElseThrow(() -> new BackeryNotFoundException(saleProductInBackeryDto.getBackeryId()));
        productService.saleProductInBackery(product, backeryDto, saleProductInBackeryDto.getAmount());
    }

    @GetMapping("/products/id/get")
    public Optional<ProductDto> getProductById(@RequestBody ProductIdDto productIdDto) {
        return productService.findProduct(productIdDto.getId());
    }

    @PutMapping("/products/id/change-price")
    public void changeProductPriceInBackery(@RequestBody ProductPriceChangeInBackeryDto productPriceChangeInBackeryDto) {
        ProductDto productDto = productService.findProduct(productPriceChangeInBackeryDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(productPriceChangeInBackeryDto.getProductId()));
        BackeryDto backeryDto = backeryService.findBackery(productPriceChangeInBackeryDto.getBackeryId())
                .orElseThrow(() -> new BackeryNotFoundException(productPriceChangeInBackeryDto.getBackeryId()));
        productService.changeProductPriceInBackery(productDto, backeryDto,productPriceChangeInBackeryDto.getPrice());
    }

    @PutMapping("/products/id/change-name")
    public void changeProductName(@PathVariable Integer id, @RequestParam String name) {
        ProductDto productDto = productService.findProduct(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productService.changeProductName(productDto, name);
    }

    @PutMapping("/products/id/change-description")
    public void changeProductDescription(@RequestBody ProductChangeNameDto productChangeNameDto) {
        ProductDto productDto = productService.findProduct(Math.toIntExact(productChangeNameDto.getId()))
                .orElseThrow(() -> new ProductNotFoundException(Math.toIntExact(productChangeNameDto.getId())));
        productService.changeProductDescription(productDto, productChangeNameDto.getDescription());
    }

    @PostMapping("/products/ingredient/add-to-product")
    public void addIngredientToProduct(@RequestBody ProductIngredientAddDto productIngredientAddDto) {
        ProductDto productDto = productService.findProduct(Math.toIntExact(productIngredientAddDto.getProductId()))
                .orElseThrow(() -> new ProductNotFoundException(Math.toIntExact(productIngredientAddDto.getProductId())));
        IngredientDto ingredientDto = ingredientService.findIngredient(productIngredientAddDto.getIngredientId())
                .orElseThrow(() -> new IngredientNotFoundException(Math.toIntExact(productIngredientAddDto.getIngredientId())));
        productService.addIngredientInProduct(productDto, ingredientDto);
    }

    @DeleteMapping("/products/ingredient/delete-from-product")
    public void deleteIngredientFromProduct(@RequestBody ProductIngredientAddDto productIngredientAddDto) {
        ProductDto productDto = productService.findProduct(Math.toIntExact(productIngredientAddDto.getProductId()))
                .orElseThrow(() -> new ProductNotFoundException(Math.toIntExact(productIngredientAddDto.getProductId())));
        IngredientDto ingredientDto = ingredientService.findIngredient(productIngredientAddDto.getIngredientId())
                .orElseThrow(() -> new IngredientNotFoundException(Math.toIntExact(productIngredientAddDto.getIngredientId())));
        productService.deleteIngredientInProduct(productDto, ingredientDto);
    }

    @PostMapping("/products/product-by-name/stocks")
    public void updateProductPriceWithStockInBackery(@RequestBody ProductUpdateStockDto productUpdateStockDto) {
        BackeryDto backeryDto = backeryService.findBackery(productUpdateStockDto.getBackeryId())
                .orElseThrow(() -> new BackeryNotFoundException(productUpdateStockDto.getBackeryId()));
        productService.makeStocksOnProductInBackery(productUpdateStockDto.getProductName(), backeryDto, productUpdateStockDto.getPercentage());
    }


//    @GetMapping("/products/bakeries/{productId}")
//    public List<BakeryDto> getAllBakeriesByProduct(@PathVariable Integer productId) {
//        ProductDto productDto = productService.findProduct(productId)
//                .orElseThrow(() -> new ProductNotFoundException(productId));
//        return productService.getAllBakeriesByProduct(productDto);
//    }

    @GetMapping("/products/search-price-in-backery")
    public Integer searchProductPricesByBackeryName(@RequestBody ProductPriceChangeInBackeryDto productPriceChangeInBackeryDto) {
        ProductDto productDto = productService.findProduct(productPriceChangeInBackeryDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(productPriceChangeInBackeryDto.getProductId()));
        BackeryDto backeryDto = backeryService.findBackery(productPriceChangeInBackeryDto.getBackeryId())
                .orElseThrow(() -> new BackeryNotFoundException(productPriceChangeInBackeryDto.getBackeryId()));
        return productService.findProductPriceByBackery(productDto, backeryDto);
    }

    @GetMapping("/products/find-products-avg-price")
    public List<ProductDto> findProductsByBackeryAndPriceRange(@RequestBody ProductMaxLowDto productMaxLowDto) {
        Optional<BackeryDto> bakeryDtoOptional = backeryService.findBackery(productMaxLowDto.getId());

        BackeryDto bakeryDto = bakeryDtoOptional.orElseThrow(() -> new BackeryNotFoundException(productMaxLowDto.getId()));

        List<Product> productList = productService.findProductsByBackeryAndPriceRange(bakeryDto, productMaxLowDto.getLowPrice(), productMaxLowDto.getMaxPrice());
        List<ProductDto> pList= new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            pList.add(modelMapper.map(productList.get(i), ProductDto.class));
        }
        return pList;
    }



//    @GetMapping("/products/search/price")
//    public List<ProductDto> searchProductsByPrice(@RequestParam Integer price) {
//        return productService.findByPrice(price);
//    }
}
