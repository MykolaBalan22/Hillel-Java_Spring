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

//    public List<Order> getAllOrders() {
//        Map<Integer, Order> orderMap = orderRepository.getAllWithProduct().stream()
//                .collect(Collectors.toMap(
//                        Order::getId, val -> val, (s1, s2) -> {
//                            s2.getProducts().addAll(s1.getProducts());
//                            return s2;
//                        }));
//        return orderMap.values().stream().toList();
//    }
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
