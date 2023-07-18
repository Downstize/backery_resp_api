package com.example.springdatabasicdemo.repositories;
import com.example.springdatabasicdemo.models.Ingredient;
import com.example.springdatabasicdemo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springdatabasicdemo.models.Product_Order;
import com.example.springdatabasicdemo.models.Order;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface ProductOrderRepository extends JpaRepository<Product_Order, Integer> {

    Set<Product_Order> findProductFromOrderById(Integer id);

    void deleteProductFromOrderById(Integer id);

    Set<Product_Order> findProductFromOrderByOrder(Order order);

    Set<Product_Order> findProduct_OrderByProduct(Product product);

    void deleteProductFromOrderByProduct(Product product);

    void deleteProduct_OrderByOrder(Order order);

}
