package com.example.glovo.services;

import com.example.glovo.entities.OrderEntity;
import com.example.glovo.entities.converters.OrderEntityConverter;
import com.example.glovo.models.Order;
import com.example.glovo.repositories.OrderDataRepository;
import com.example.glovo.repositories.ProductDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

@Service
public class OrderService {
    private final OrderDataRepository orderRepository;

    private final ProductDataRepository productRepository;

    @Autowired
    public OrderService(OrderDataRepository orderRepository, ProductDataRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order getOrderById(int id) {
        OrderEntity entity = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        return  OrderEntityConverter.orderEntityToOrder(entity ,productRepository.getProductEntityByCertainOrder(id));
    }

    public List<Order> getAllOrders() {
        Iterator<OrderEntity> iterator = orderRepository.findAll().iterator();
        List<Order> orders= new ArrayList<>();
        while (iterator.hasNext()) {
            OrderEntity entity = iterator.next();
            orders.add(OrderEntityConverter.orderEntityToOrder(entity,productRepository.getProductEntityByCertainOrder(entity.getId())));
        }
        return orders;
    }
//
//    public Order addOrder(Order order) {
//        order.setId(ThreadLocalRandom.current().nextInt(1, 20000));
//        Order savedOrder = orderRepository.add(order);
//        List<Integer> productsId = order.getProducts().stream()
//                .map(Product::getId)
//                .toList();
//        List<Product> savedProductList = productRepository.addProductsForOrder(order.getId(), productsId);
//        savedOrder.setProducts(savedProductList);
//        return savedOrder;
//    }
//
//    public Order updateOrder(Order order) {
//        Order updatedOrder = orderRepository.update(order);
//        updatedOrder.setProducts(productRepository.updateProductsByCertainOrder(order.getId() ,order.getProducts().stream().map(Product::getId).toList()));
//        return updatedOrder;
//    }
//
//    public boolean removeOrder(Order order) {
//        boolean deletedInAllOrdersWithProducts = orderRepository.removeOrderWithProducts(order.getId());
//        boolean deletedInOrders = orderRepository.remove(order.getId());
//        return deletedInOrders && deletedInAllOrdersWithProducts;
//    }
}
