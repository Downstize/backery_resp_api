package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.BackeryDto;
import com.example.springdatabasicdemo.dtos.IngredientDto;
import com.example.springdatabasicdemo.dtos.ProductDto;
import com.example.springdatabasicdemo.models.*;
import com.example.springdatabasicdemo.repositories.BackeryRepository;
import com.example.springdatabasicdemo.repositories.IngredientRepository;
import com.example.springdatabasicdemo.repositories.ProductBackeryRepository;
import com.example.springdatabasicdemo.repositories.ProductRepository;
import com.example.springdatabasicdemo.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService<Integer> {

    @Autowired
    private BackeryRepository backeryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private ProductBackeryRepository productBackeryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto cookProduct(ProductDto productDto, BackeryDto backeryDto, Integer amount, Integer price) {
        // Create a new Product
        Product product = new Product(productDto.getName(), productDto.getDescription());
        productRepository.save(product);

        // Fetch the backery entity from the database to bring it into the persistence context
        Backery backery = backeryRepository.findBackeriesById(backeryDto.getId());

        // Check if the product is already in the backery
        boolean productAlreadyExistsInBackery = backery.getProductBackeries().stream()
                .anyMatch(existingProductBackery -> existingProductBackery.getProduct().equals(product));

        if (productAlreadyExistsInBackery) {
            // Update the amount of the existing product in the backery
            backery.getProductBackeries().stream()
                    .filter(existingProductBackery -> existingProductBackery.getProduct().equals(product))
                    .findFirst()
                    .ifPresent(existingProductBackery -> existingProductBackery.setAmount(existingProductBackery.getAmount() + amount));
        } else {
            // Create a new ProductBackery with the given amount and associate it with the product and backery
            ProductBackeryKey productBackeryKey = new ProductBackeryKey(product.getId(), backery.getId());
            ProductBackery productBackery = new ProductBackery(productBackeryKey, product, backery, price, amount);

            // Add the productBackery to the product's productBackeries set
            product.getProductBackeries().add(productBackery);

            // Add the productBackery to the backery's productBackeries set
            backery.getProductBackeries().add(productBackery);
        }

        // Save the backery to bring the productBackeries into the persistent state
        backeryRepository.save(backery);

        // Save the product again after updating productBackeries
        return modelMapper.map(productRepository.save(product), ProductDto.class);
    }
    @Override
    public ProductDto  cookProduct2(ProductDto productDto, BackeryDto backeryDto, Integer amount, Integer price) {
        // Create a new Product
        Product product = new Product(productDto.getName(), productDto.getDescription());
        productRepository.save(product);

        // Fetch the bakery entity from the database to bring it into the persistence context
        Backery backery = backeryRepository.findBackeriesById(backeryDto.getId());

        // Check if the product is already in the bakery
        boolean productAlreadyExistsInBackery = backery.getProductBackeries().stream()
                .anyMatch(existingProductBackery -> existingProductBackery.getProduct().equals(product));

        if (productAlreadyExistsInBackery) {
            // Update the amount of the existing product in the bakery using the custom query
            productRepository.updateProductBackeryAmount(product, backery, amount);
        } else {
            // Create a new ProductBackery with the given amount and associate it with the product and bakery
            ProductBackeryKey productBackeryKey = new ProductBackeryKey(product.getId(), backery.getId());
            ProductBackery productBackery = new ProductBackery(productBackeryKey, product, backery, price, amount);

            // Add the productBackery to the product's productBackeries set
            product.getProductBackeries().add(productBackery);

            // Add the productBackery to the bakery's productBackeries set
            backery.getProductBackeries().add(productBackery);
        }

        // Save the bakery to bring the productBackeries into the persistent state
        backeryRepository.save(backery);

        // Save the product again after updating productBackeries
        return modelMapper.map(productRepository.save(product), ProductDto.class);

    }

//    public ProductDto cookProduct(ProductDto productDto, BackeryDto backeryDto, Integer amount) {
//        Product product = new Product(productDto.getName(), productDto.getPrice(), productDto.getDescription());
//        productRepository.save(product);
//        Backery backery = backeryRepository.findById(backeryDto.getId())
//                .orElseThrow(() -> new IllegalArgumentException("Backery not found"));
//        boolean productAlreadyExistsInBackery = false;
//        for (ProductBackery existingProductBackery : backery.getProductBackeries()) {
//            if (existingProductBackery.getProduct().equals(product)) {
//                existingProductBackery.setAmount(existingProductBackery.getAmount() + amount);
//                productAlreadyExistsInBackery = true;
//                break;
//            }
//        }
//        if (!productAlreadyExistsInBackery) {
//            ProductBackery productBackery = new ProductBackery(product, backery, amount);
//            product.getProductBackeries().add(productBackery);
//            backery.getProductBackeries().add(productBackery);
//            backeryRepository.save(backery);
//        }
//        return modelMapper.map(productRepository.save(product), ProductDto.class);
//    }

    public List<Backery> findBakeriesByProductName(String productName) {
        Product product = productRepository.findProductByName(productName);
        List<Backery> bakeries = new ArrayList<>();
        if (product != null) {
            for (ProductBackery productBackery : product.getProductBackeries()) {
                bakeries.add(productBackery.getBackery());
            }
        }
        return bakeries;
    }


    @Override
    public void saleProductInBackery(ProductDto productDto, BackeryDto backeryDto, int soldAmount) {
        // Retrieve the Product and Backery entities based on the provided DTOs
        Product product = productRepository.findProductByName(productDto.getName());
        Backery backery = backeryRepository.findBackeryByName(backeryDto.getName());

        if (product == null || backery == null) {
            // Handle cases where the Product or Backery is not found in the database
            throw new IllegalArgumentException("Product or Backery not found.");
        }

        // Check if the product exists in the backery
        ProductBackery productBackery = null;
        for (ProductBackery existingProductBackery : backery.getProductBackeries()) {
            if (existingProductBackery.getProduct().equals(product)) {
                productBackery = existingProductBackery;
                break;
            }
        }

        if (productBackery == null) {
            // Handle cases where the product is not found in the backery
            throw new IllegalArgumentException("Product not found in the Backery.");
        }

        int currentAmount = productBackery.getAmount();

        if (soldAmount > currentAmount) {
            // Handle cases where the sold amount is more than what's available in the backery
            throw new IllegalArgumentException("Not enough quantity available in the Backery.");
        }

        // Update the amount in the backery after the sale
        productBackery.setAmount(currentAmount - soldAmount);

        if ((currentAmount - soldAmount) == 0) {
            // If the current amount is null or the sold amount is more than what's available in the backery,
            // delete the record as there's nothing left to sell.
            backery.getProductBackeries().remove(productBackery);

            // Save the updated entities to the database
            // Assuming you have a method to update the entities in the database
            if (productBackery != null) {
                productBackeryRepository.save(productBackery);
            }
        }
    }

//    @Override
//    public void saleProductByPrice(ProductDto product) {
//        productRepository.deleteProductByPrice(product.getPrice());
//    }
//
//    @Override
//    public void saleProductByName(ProductDto product) {
//        productRepository.deleteProductByName(product.getName());
//    }
//
//    @Override
//    public void saleProduct(Integer id) {
//        productRepository.deleteProductById(id);
//    }

//    @Override
//    public void changeProductRecipe(ProductDto product, IngredientDto ingredient1, IngredientDto ingredient2) {
//
//    }

    @Override
    public void changeProductPriceInBackery(ProductDto productDto, BackeryDto backeryDto, Integer price) {
        // Retrieve the ProductBackery entity based on the provided product and bakery IDs
        ProductBackery productBackery = productBackeryRepository.findByProductIdAndBackeryId(
                productDto.getId(), backeryDto.getId()
        );

        if (productBackery == null) {
            // Handle cases where the ProductBackery is not found in the database
            throw new IllegalArgumentException("Product not found in the specified bakery.");
        }

        // Update the product's price in the bakery
        productBackery.setPrice(price);
        productBackeryRepository.save(productBackery);
    }


    @Override
    public void changeProductName(ProductDto productDto, String name) {
        // Retrieve the Product entity based on the provided product name
        Product product = productRepository.findProductByName(productDto.getName());

        if (product == null) {
            // Handle cases where the Product is not found in the database
            throw new IllegalArgumentException("Product not found.");
        }

        // Update the product's price
        product.setName(name);
        productRepository.save(product);
    }

    @Override
    public void changeProductDescription(ProductDto productDto, String description) {
        // Retrieve the Product entity based on the provided product name
        Product product = productRepository.findProductByName(productDto.getName());

        if (product == null) {
            // Handle cases where the Product is not found in the database
            throw new IllegalArgumentException("Product not found.");
        }

        // Update the product's price
        product.setDescription(description);
        productRepository.save(product);
    }

    @Override
    public void deleteIngredientInProduct(ProductDto productDto, IngredientDto ingredientDto) {
        // Retrieve the Product and Ingredient entities based on the provided DTOs
        Product product = productRepository.findProductByName(productDto.getName());
        Ingredient ingredient = ingredientRepository.findIngredientByName(ingredientDto.getName());

        if (product == null || ingredient == null) {
            // Handle cases where the Product or Ingredient is not found in the database
            throw new IllegalArgumentException("Product or Ingredient not found.");
        }

        // Check if the product contains the specified ingredient
        ProductIngredient productIngredientToDelete = null;
        for (ProductIngredient productIngredient : product.getProductIngredient()) {
            if (productIngredient.getIngredient().equals(ingredient)) {
                productIngredientToDelete = productIngredient;
                break;
            }
        }

        if (productIngredientToDelete == null) {
            // Handle cases where the ingredient is not found in the product
            throw new IllegalArgumentException("Ingredient not found in the Product.");
        }

        // Remove the ingredient from the product's ingredients
        product.getProductIngredient().remove(productIngredientToDelete);

        // Save the updated product to the database
        // Assuming you have a method to persist the product to the database
        productRepository.save(product);
    }



    @Override
    public void addIngredientInProduct(ProductDto productDto, IngredientDto ingredientDto) {
        // Retrieve the Product and Ingredient entities based on the provided DTOs
        Product product = productRepository.findProductByName(productDto.getName());
        Ingredient ingredient = ingredientRepository.findIngredientByName(ingredientDto.getName());

        if (product == null || ingredient == null) {
            // Handle cases where the Product or Ingredient is not found in the database
            throw new IllegalArgumentException("Product or Ingredient not found.");
        }

        // Check if the product already contains the specified ingredient
        for (ProductIngredient productIngredient : product.getProductIngredient()) {
            if (productIngredient.getIngredient().equals(ingredient)) {
                // Handle cases where the ingredient is already present in the product
                throw new IllegalArgumentException("Ingredient already exists in the Product.");
            }
        }

        // Add the ingredient to the product's ingredients
        ProductIngredient newProductIngredient = new ProductIngredient(product, ingredient);
        product.getProductIngredient().add(newProductIngredient);

        // Save the updated product to the database
        // Assuming you have a method to persist the product to the database
        productRepository.save(product);
    }



    @Override
    public void makeStocksOnProductInBackery(String productName, BackeryDto backeryDto, double percentage) {
        // Retrieve the ProductBackery entity based on the provided product name and bakery ID
        ProductBackery productBackery = productBackeryRepository.findByProductNameAndBackeryId(
                productName, backeryDto.getId()
        );

        if (productBackery == null) {
            // Handle cases where the ProductBackery is not found in the database
            throw new IllegalArgumentException("Product not found in the specified bakery.");
        }

        // Calculate the new price after applying the percentage stock
        double currentPrice = productBackery.getPrice();
        double stockFactor = 1 + (percentage / 100);
        double newPrice = currentPrice * stockFactor;

        // Round the new price to two decimal places to ensure currency format
        newPrice = Math.round(newPrice * 100.0) / 100.0;

        // Update the product's price in the bakery with the new calculated price
        productBackery.setPrice((int) Math.round(newPrice));

        // Save the updated ProductBackery entity to the database
        // Assuming you have a method to persist the entity to the database
        productBackeryRepository.save(productBackery);
    }





//    @Override
//    public List<BakeryDto> getAllBakeriesByProduct(ProductDto product) {
//        List<Bakery> bakeries = bakeryRepository.findBakeryByProducts(modelMapper.map(product, Product.class));
//        return bakeries.stream()
//                .map(bakery -> modelMapper.map(bakery, BakeryDto.class))
//                .collect(Collectors.toList());
//    }

    @Override
    public Optional<ProductDto> findProductById(Long id) {
        Optional<Product> productOptional = productRepository.findProductById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            ProductDto productDto = modelMapper.map(product, ProductDto.class);
            return Optional.of(productDto);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProductDto> findProduct(Integer id) {
        return Optional.ofNullable(modelMapper.map(productRepository.findById(id), ProductDto.class));
//        return modelMapper.map(productRepository.findById(id), ProductDto.class);
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map((p) -> modelMapper.map(p, ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public Integer findProductPriceByBackery(ProductDto productDto, BackeryDto backeryDto) {
        // Retrieve the Product entity based on the provided product name
        Product product = productRepository.findProductByName(productDto.getName());

        if (product == null) {
            throw new IllegalArgumentException("Product not found.");
        }

        // Retrieve the ProductBackery entity for the product and backery combination
        ProductBackery productBackery = productBackeryRepository.findByProductIdAndBackeryId(
                product.getId(), backeryDto.getId()
        );

        if (productBackery == null) {
            throw new IllegalArgumentException("Product not found in the specified bakery.");
        }

        return productBackery.getPrice();
    }




//    @Override
//    public List<ProductDto> findByPriceInBackery(BackeryDto backeryDto, Integer price) {
//        return productRepository.findProductsByPrice(price).stream().map((p) -> modelMapper.map(p, ProductDto.class)).collect(Collectors.toList());
//    }

    @Override
    public List<Product> findProductsByBackeryAndPriceRange(BackeryDto backeryDto, double minPrice, double maxPrice) {
        return productRepository.findProductsByBackeryAndPriceRange(backeryDto.getName(), minPrice, maxPrice);
    }



//    @Override
//    public List<ProductDto> findByDescription(String description) {
//        return productRepository.findProductsByDescription(description).stream().map((p) -> modelMapper.map(p, ProductDto.class)).collect(Collectors.toList());
//    }
}
