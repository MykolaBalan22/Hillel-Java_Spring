package com.example.glovo.services;

import com.example.glovo.entities.OrderEntity;
import com.example.glovo.entities.ProductEntity;
import com.example.glovo.entities.converters.OrderEntityConverter;
import com.example.glovo.entities.converters.ProductEntityConverter;
import com.example.glovo.models.Order;
import com.example.glovo.models.Product;
import com.example.glovo.repositories.OrderDataRepository;
import com.example.glovo.repositories.ProductDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
        return OrderEntityConverter.orderEntityToOrder(entity, productRepository.getProductEntityByCertainOrder(id));
    }

    public List<Order> getAllOrders() {
        Iterator<OrderEntity> iterator = orderRepository.findAll().iterator();
        List<Order> orders = new ArrayList<>();
        while (iterator.hasNext()) {
            OrderEntity entity = iterator.next();
            orders.add(OrderEntityConverter.orderEntityToOrder(entity, productRepository.getProductEntityByCertainOrder(entity.getId())));
        }
        return orders;
    }

    public Order addOrder(Order order) {
        OrderEntity entity = orderRepository.save(OrderEntityConverter.orderToOrderEntity(order));
        for (Product product : order.getProducts()) {
            productRepository.addProductByOrder(entity.getId(), product.getId(), LocalDate.now());
        }
        List<ProductEntity> products = productRepository.getProductEntityByCertainOrder(entity.getId());
        return OrderEntityConverter.orderEntityToOrder(entity, products);
    }

    public Order updateOrder(Order order) {
        OrderEntity changedOrderEntity = orderRepository.save(OrderEntityConverter.orderToOrderEntity(order));
        productRepository.deleteProductsForOrder(order.getId());
        for (Product product : order.getProducts()) {
            productRepository.addProductByOrder(order.getId(), product.getId(), LocalDate.now());
        }
        List<ProductEntity> products = productRepository.getProductEntityByCertainOrder(order.getId());
        return OrderEntityConverter.orderEntityToOrder(changedOrderEntity, products);
    }

    public boolean removeOrder(Order order) {
        productRepository.deleteProductsForOrder(order.getId());
        int numberOfOrderProducts = productRepository.countOrderProductsByOrderId(order.getId());
        orderRepository.deleteById(order.getId());
        boolean present = orderRepository.findById(order.getId()).isPresent();
        return (numberOfOrderProducts == 0) && !present;
    }
}
