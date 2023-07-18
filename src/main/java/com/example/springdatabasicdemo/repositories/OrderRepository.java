package com.example.springdatabasicdemo.repositories;
import com.example.springdatabasicdemo.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findOrderById(Integer id);
    
    List<Order> findOrderByTime(LocalDateTime time);
    
    List<Order> findOrderByClient(Client client);
    
    List<Order> findOrderByStatus(String status);
    
    void deleteOrderById(Integer id);
    
    void deleteOrderByClient(Client client);
    
    void deleteOrderByStatus(String status);
    
    void deleteOrderByTime(LocalDateTime time);
}
