package com.example.glovo.services;

import com.example.glovo.models.Order;
import com.example.glovo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrderById(int id) {
        return null;
    }

    public List<Order> getAllOrders() {
        return null;
    }

    public Order addOrder(Order order) {
        return null;
    }
}
