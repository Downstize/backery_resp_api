package com.example.springdatabasicdemo.services.impl;
import com.example.springdatabasicdemo.dtos.ClientDto;
import com.example.springdatabasicdemo.dtos.OrderDto;
import com.example.springdatabasicdemo.dtos.ProductDto;
import com.example.springdatabasicdemo.models.*;
import com.example.springdatabasicdemo.repositories.ClientRepository;
import com.example.springdatabasicdemo.repositories.OrderRepository;
import com.example.springdatabasicdemo.repositories.ProductRepository;
import com.example.springdatabasicdemo.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService<Integer> {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper modelMapper;

//     @Override
//     @Transactional
//     public OrderDto registerNewOrder(OrderDto orderDto, ClientDto clientDto) {
//         Order order = modelMapper.map(orderDto, Order.class);
//         Client client = clientRepository.findById(clientDto.getId()).orElse(null);
//         order.setClient(client);
//
//         if (orderDto.getProducts() != null) {
//             Set<Product> products = new HashSet<>();
//             for (ProductDto productDto : orderDto.getProducts()) {
//                 if (productDto != null) {
//                     Product product = productRepository.findById(productDto.getId()).orElse(null);
//                     if (product != null) {
//                         products.add(product);
//                     }
//                 }
//             }
//             order.setProducts(products);
//         }
//
//         Order savedOrder = orderRepository.save(order);
//         return modelMapper.map(savedOrder, OrderDto.class);
//     }


//     @Override
//     public OrderDto registerNewOrder(ClientDto clientDto, OrderDto orderDto, ProductDto productDto) {
//         // Create a new order object
//         orderDto.setOrderTime(LocalDateTime.now());
//         orderDto.setStatus("New");
//
//         // Convert ClientDto and OrderDto to entities
//         Client client = modelMapper.map(clientDto, Client.class);
//         Order order = modelMapper.map(orderDto, Order.class);
//
//         // Associate the order with the client
//         order.setClient(client);
//         orderRepository.save(order);
//
//         // Create a Product entity from the ProductDto
//         Product product = new Product(productDto.getName(), productDto.getPrice(), productDto.getDescription());
//
//         // Create a Product_Order object and associate it with the order
//         Product_Order productOrder = new Product_Order(product, order);
//
//         // Create a set of Product_Order objects and add the productOrder to it
//         Set<Product_Order> productOrders = new HashSet<>();
//         productOrders.add(productOrder);
//
//         product.getOrders().add(productOrder);
//         order.getOrderProducts().add(productOrder);
//
//
//         // Add the productBackery to the backery's productBackeries set
//         backery.getProductBackeries().add(productBackery);
//
//         // Associate the products with the order
//         order.setProducts(productOrders);
//
//         // Save the order to the databas
//
//         return modelMapper.map(orderRepository.save(order), OrderDto.class);
//     }

    @Override
    public OrderDto registerNewOrder(ClientDto clientDto, OrderDto orderDto) {
        // Create a new order object
        orderDto.setOrderTime(LocalDateTime.now());
//        orderDto.setStatus("New");

        // Convert ClientDto and OrderDto to entities
        Client client = modelMapper.map(clientDto, Client.class);
        Order order = modelMapper.map(orderDto, Order.class);

        // Associate the order with the client
        order.setClient(client);

//        // Create a Product_Order object and associate it with the order and product
//        Product_Order productOrder = new Product_Order(product, order);
//        productOrder.setOrder(order); // Set the order on the Product_Order side of the relationship
//        productOrder.setProduct(product); // Set the product on the Product_Order side of the relationship
//        order.getOrderProducts().add(productOrder); // Add the Product_Order to the order's product set

        // Save the order to the database
        //orderRepository.save(order);

        return modelMapper.map(orderRepository.save(order), OrderDto.class);
    }





//    public void registerNewOrder(ClientDto clientDto, OrderDto orderDto, List<ProductDto> products) {
//            // Create a new order object
//            orderDto.setOrderTime(LocalDateTime.now());
//            orderDto.setStatus("New");
//            Client client = modelMapper.map(clientDto, Client.class);
//            Order order = modelMapper.map(orderDto, Order.class);
//
//            // Associate the order with the client
//            orderDto.setClient(clientDto);
//            List<Order> orders = new ArrayList<>();
//            orders.add(order);
//            client.getOrders().addAll(orders);
//
//            // Create a set of Product_Order objects for each product in the order
//            Set<Product_Order> productOrders = new HashSet<>();
//            for (ProductDto productDto : products) {
//                Product_Order productOrder = new Product_Order(modelMapper.map(productDto, Product.class), order);
//                productOrders.add(productOrder);
//            }
//
//            // Associate the products with the order
//            order.setProducts(productOrders);
//            orderRepository.save(order);
//        }




//     @Override
//     public OrderDto addProductsInNewOrder(OrderDto order, List<ProductDto> productDtos) {
//         return null;
//     }

//     @Override
//     @Transactional
//     public OrderDto addProductsInNewOrder(OrderDto order, Set<Product_Order> orderProducts) {
//         Order newOrder = modelMapper.map(order, Order.class);
//         List<Product> products = orderProducts.stream()
//                 .map(productDto -> {
//                     Product product = productRepository.findById(orderProducts.getId()).orElse(null);
//                     if (product != null) {
//                         return modelMapper.map(orderProducts, Product.class);
//                     } else {
//                         return null;
//                     }
//                 })
//                 .filter(Objects::nonNull)
//                 .collect(Collectors.toList());
//         newOrder.setProducts(orderProducts);
//
//         // Save the new order
//         Order savedOrder = orderRepository.save(newOrder);
//
//         // Map the saved order back to OrderDto
//         OrderDto savedOrderDto = modelMapper.map(savedOrder, OrderDto.class);
//
//         return savedOrderDto;
//     }


    @Override
    public OrderDto addProductsInNewOrder(OrderDto orderDto, ProductDto orderProducts) {
        Order order = modelMapper.map(orderDto, Order.class);
        Product product = modelMapper.map(orderProducts, Product.class);

        Product_Order productOrder = new Product_Order(product, order);
        product.getOrders().add(productOrder);
        order.getOrderProducts().add(productOrder);

//        if (product.getId() == 0) {
//            product = productRepository.save(product);
//        }

        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderDto.class);
    }




    @Override
    public void deleteOrder(Long integer) {
        orderRepository.deleteOrderById(Math.toIntExact(integer));
    }

    @Override
    public void deleteOrdersByStatus(OrderDto order) {
        orderRepository.deleteOrderByStatus(order.getStatus());
    }

    @Override
    public void deleteOrderByClient(ClientDto client) {
        Client c = modelMapper.map(client, Client.class);
        orderRepository.deleteOrderByClient(c);
    }


    @Override
    public Optional<OrderDto> findOrder(Integer integer) {
       return Optional.ofNullable(modelMapper.map(orderRepository.findOrderById(integer), OrderDto.class));
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();

        for (Order order : orders) {
            OrderDto orderDto = modelMapper.map(order, OrderDto.class);
            orderDtos.add(orderDto);
        }

        return orderDtos;
    }


    @Override
    public List<OrderDto> findOrdersByClient(ClientDto client) {
        List<Order> orders = orderRepository.findOrderById(client.getId());
        return orders.stream().map(order -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
    }

    @Override
    public void changeOrderStatus(OrderDto order, String status) {
        Order o = modelMapper.map(order.getStatus(), Order.class);
        o.setStatus(status);
        orderRepository.save(o);
    }

    @Override
    public void changeOrderClient(OrderDto order, ClientDto client) {
        Order o = modelMapper.map(order, Order.class);
        Client c = modelMapper.map(client,Client.class);

        o.setClient(c);
        orderRepository.save(o);
    }

    @Override
    public void addOrderProduct(OrderDto order, ProductDto product) {
        Order o = modelMapper.map(order, Order.class);
        Product p = modelMapper.map(product, Product.class);
        if (o.getOrderProducts() != null && p != null) {
            // Check if the product already exists in the set
            boolean productExists = o.getOrderProducts().stream()
                    .anyMatch(existingProduct -> existingProduct.getProduct().getId().equals(p.getId()));

            if (!productExists) {
                // Create a new Product_Order object
                Product_Order productOrder = new Product_Order(p, o);

                // Add the productOrder to the set
                o.getOrderProducts().add(productOrder);
                orderRepository.save(o);
            }
        }
    }


    @Override
    public void deleteOrderProduct(OrderDto order, ProductDto product) {
        Order o = modelMapper.map(order, Order.class);
        Product p = modelMapper.map(product, Product.class);
        if (o.getOrderProducts() != null && p != null) {
            o.getOrderProducts().removeIf(existingIngredient -> existingIngredient.getProductOrderId().equals(p.getId()));
            orderRepository.save(o);
        }
    }

}
