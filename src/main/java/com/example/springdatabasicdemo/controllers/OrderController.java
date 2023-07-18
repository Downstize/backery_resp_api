package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.ClientDto;
import com.example.springdatabasicdemo.dtos.OrderDto;
import com.example.springdatabasicdemo.dtos.ProductDto;
import com.example.springdatabasicdemo.dtos.OrderDeleteOrderByIdDto;
import com.example.springdatabasicdemo.dtos.OrderDeleteOrderByStatusDto;
import com.example.springdatabasicdemo.dtos.OrderRegistrationRequestDto;
import com.example.springdatabasicdemo.dtos.AddOrderProductsRequestDto;
import com.example.springdatabasicdemo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    private final OrderService<Integer> orderService;

    @Autowired
    public OrderController(OrderService<Integer> orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/register/order")
    public void registerNewOrder(@RequestBody OrderRegistrationRequestDto request) {
        orderService.registerNewOrder(request.getClient(), request.getOrder());
    }


    @DeleteMapping("/delete/order/id")
    public void deleteOrderById(@RequestBody OrderDeleteOrderByIdDto orderDelById) {
        orderService.deleteOrder(orderDelById.getId());
    }

    @DeleteMapping("/delete/order/status")
    public void deleteOrdersByStatus(@RequestBody OrderDeleteOrderByStatusDto orderDelByStatus) {
        orderService.deleteOrdersByStatus(orderDelByStatus.getOrder());
    }

    @DeleteMapping("/delete/order/client")
    public void deleteOrderByClient(@RequestBody ClientDto client) {
        orderService.deleteOrderByClient(client);
    }

    @GetMapping("/order/id")
    public Optional<OrderDto> getOrderById(@RequestBody Integer id) {
        return orderService.findOrder(id);
    }

    @GetMapping("/orders")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/client")
    public List<OrderDto> getOrdersByClient(@RequestBody ClientDto client) {
        return orderService.findOrdersByClient(client);
    }

    @PutMapping("/change/order/status")
    public void changeOrderStatus(@RequestBody Integer id, @RequestBody String status) {
        OrderDto orderDto = orderService.findOrder(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        orderService.changeOrderStatus(orderDto, status);
    }

    @PutMapping("/change/order/client")
    public void changeOrderClient(@RequestBody Integer id, @RequestBody ClientDto client) {
        OrderDto orderDto = orderService.findOrder(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        orderService.changeOrderClient(orderDto, client);
    }

    @PostMapping("/add/order/product/id")
    public void addOrderProduct(@RequestBody AddOrderProductsRequestDto request) {
        Integer orderId = request.getId();
        ProductDto product = request.getProduct();

        OrderDto orderDto = orderService.findOrder(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        if (orderDto.getProducts() == null) {
            orderDto.setProducts(new ArrayList<>());
        }

        orderService.addProductsInNewOrder(orderDto, product);
    }









    @DeleteMapping("/delete/order/product/id")
    public void deleteOrderProduct(@RequestBody Integer id, @RequestBody ProductDto product) {
        OrderDto orderDto = orderService.findOrder(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        orderService.deleteOrderProduct(orderDto, product);
    }
}
