package com.example.glovo.controllers;

import com.example.glovo.models.Order;
import com.example.glovo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Order getCertainOrder(@PathVariable Integer id) {
        return service.getOrderById(id);
    }

    @GetMapping
    public List<Order> getOrderList() {
        return service.getAllOrders();
    }

    @PostMapping
    public Order insertNewOrder(@RequestBody Order order) {
        return service.addOrder(order);
    }

    @PutMapping
    public Order changeOrder(@RequestBody Order order) {
        return service.updateOrder(order);
    }

    @DeleteMapping
    public boolean deleteOrder(@RequestBody Order order) {
        return service.removeOrder(order);
    }

}
