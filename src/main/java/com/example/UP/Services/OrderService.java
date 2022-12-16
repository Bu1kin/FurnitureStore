package com.example.UP.Services;

import com.example.UP.Models.Order;
import com.example.UP.Securing.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface OrderService {
    MessageResponse createOrder(Order order);
    Optional<Order> updateOrder(Long id, Order order);
    void deleteOrder(Long id);
    Order getASingleOrder(Long id);
    List<Order> getAllOrders();
}
