package com.example.glovo.services;

import com.example.glovo.models.Order;
import com.example.glovo.repositories.OrderRepository;
import com.example.glovo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order getOrderById(int id) {
        Order order = orderRepository.getOrder(id);
        order.setProducts(productRepository.getProductsByCertainOrder(id));
        return order;
    }

    public List<Order> getAllOrders() {
        return null;
    }

    public Order addOrder(Order order) {
        return null;
    }
}
