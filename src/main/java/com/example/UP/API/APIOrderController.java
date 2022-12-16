package com.example.UP.API;

import com.example.UP.Models.Order;
import com.example.UP.Securing.MessageResponse;
import com.example.UP.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class APIOrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id){
        Order order = orderService.getASingleOrder(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Order>> updateOrder(@PathVariable Long id, @RequestBody Order order){
        Optional<Order> updateOrder = orderService.updateOrder(id, order);
        return new ResponseEntity<>(updateOrder, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> createOrder(@RequestBody Order order){
        MessageResponse newOrder = orderService.createOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id){
        orderService.deleteOrder(id);
    }
}
