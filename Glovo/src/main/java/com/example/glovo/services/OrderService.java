package com.example.glovo.services;

import com.example.glovo.models.Order;
import com.example.glovo.models.Product;
import com.example.glovo.repositories.OrderRepository;
import com.example.glovo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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
        Map<Integer, Order> orderMap = orderRepository.getAllWithProduct().stream()
                .collect(Collectors.toMap(
                        Order::getId, val -> val, (s1, s2) -> {
                            s2.getProducts().addAll(s1.getProducts());
                            return s2;
                        }));
        return orderMap.values().stream().toList();
    }

    public Order addOrder(Order order) {
        order.setId(ThreadLocalRandom.current().nextInt(1, 20000));
        Order savedOrder = orderRepository.add(order);
        List<Integer> productsId = order.getProducts().stream()
                .map(Product::getId)
                .toList();
        List<Product> savedProductList = productRepository.addProductsForOrder(order.getId(), productsId);
        savedOrder.setProducts(savedProductList);
        return savedOrder;
    }
}
