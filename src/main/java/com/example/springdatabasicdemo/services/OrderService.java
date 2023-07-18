package com.example.springdatabasicdemo.services;
import com.example.springdatabasicdemo.dtos.OrderDto;
import com.example.springdatabasicdemo.dtos.ClientDto;
import com.example.springdatabasicdemo.dtos.ProductDto;
import com.example.springdatabasicdemo.models.Product_Order;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OrderService<ID> {

    OrderDto registerNewOrder(ClientDto clientDto, OrderDto orderDto);
    
    OrderDto addProductsInNewOrder(OrderDto orderDto, ProductDto orderProducts);

    void deleteOrder(Long id);

    void deleteOrdersByStatus(OrderDto order);

    void deleteOrderByClient(ClientDto client);

    Optional<OrderDto> findOrder(ID id);

    List<OrderDto> getAllOrders();

    List<OrderDto> findOrdersByClient(ClientDto client);

    void changeOrderStatus(OrderDto order, String status);

    void changeOrderClient(OrderDto order, ClientDto client);

    void addOrderProduct(OrderDto order, ProductDto product);
    
    void deleteOrderProduct(OrderDto order, ProductDto product);

}
