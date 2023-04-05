package com.example.glovo.services;

import com.example.glovo.models.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final List<Order> ordersStorage =new ArrayList<>();
    public Order getOrderById(int id) {
        return ordersStorage.stream().filter(order->order.getId()==id).findFirst().orElseThrow();
    }

    public List<Order> getAllOrders() {
        return ordersStorage;
    }

    public Order addOrder(Order order) {
         ordersStorage.add(order);
         return order;
    }
}
