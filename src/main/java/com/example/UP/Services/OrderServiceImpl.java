package com.example.UP.Services;

import com.example.UP.APIRepos.APIOrderRepo;
import com.example.UP.Models.Order;
import com.example.UP.Models.Product;
import com.example.UP.Securing.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    APIOrderRepo apiOrderRepo;

    @Override
    public MessageResponse createOrder(Order orderRequest){
        Order order = new Order();
        order.setDateOfRelease(orderRequest.getDateOfRelease());
        order.setClient(orderRequest.getClient());
        order.setEmployee(orderRequest.getEmployee());

        apiOrderRepo.save(order);
        return new MessageResponse("Поставщик успешно создан.");
    }

    @Override
    public Optional<Order> updateOrder(Long id, Order orderRequest){
        Optional<Order> order = apiOrderRepo.findById(id);
        if (order.isEmpty()){
            return null;
        } else {
            order.get().setDateOfRelease(orderRequest.getDateOfRelease());
            order.get().setClient(orderRequest.getClient());
            order.get().setEmployee(orderRequest.getEmployee());

            apiOrderRepo.save(order.get());
            return order;
        }
    }

    @Override
    public void deleteOrder(Long id){
        apiOrderRepo.deleteById(id);
    }

    @Override
    public Order getASingleOrder(Long id){
        return apiOrderRepo.findById(id).orElseThrow();
    }

    @Override
    public List<Order> getAllOrders(){
        return apiOrderRepo.findAll();
    }
}
