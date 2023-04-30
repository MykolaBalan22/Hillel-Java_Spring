package com.example.glovo.services;

import com.example.glovo.entities.OrderEntity;
import com.example.glovo.entities.ProductEntity;
import com.example.glovo.models.Order;
import com.example.glovo.models.Product;
import com.example.glovo.repositories.OrderDataRepository;
import com.example.glovo.repositories.OrderWithProductsRepository;
import com.example.glovo.repositories.ProductDataRepository;
import com.sun.jdi.request.InvalidRequestStateException;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderDataRepository orderRepository;
    @Mock
    private ProductDataRepository productDataRepository;
    @Mock
    private OrderWithProductsRepository orderWithProductsRepository;

    @Test
    public void getOrderByIdTest() {
        OrderService orderService = new OrderService(orderRepository, orderWithProductsRepository, productDataRepository);
        List<Product> products = List.of(Product.builder()
                .name("Apple")
                .id(2)
                .cost(45.6)
                .dateOfPull(LocalDate.now())
                .build());
        Order expected = Order.builder()
                .id(23)
                .date(LocalDate.now())
                .cost(34.6)
                .products(products)
                .build();
        Mockito.doReturn(Optional.of(OrderEntity.builder()
                .id(23).date(LocalDate.now())
                .cost(34.6)
                .build())).when(orderRepository).findById(23);
        Mockito.doReturn(List.of(ProductEntity.builder()
                .name("Apple")
                .id(2)
                .cost(45.6)
                .build())).when(productDataRepository).getProductEntityByCertainOrder(23);
        Order actual = orderService.getOrderById(23);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getOrderByIdExeptionTest() {
        OrderService orderService = new OrderService(orderRepository, orderWithProductsRepository, productDataRepository);
        Mockito.doThrow(ResponseStatusException.class).when(orderRepository).findById(-23);
        Assertions.assertThrows(ResponseStatusException.class, () -> orderService.getOrderById(-23));
    }

    @Test
    public void getAllOrders() {

    }

    @Test
    public void addOrder() {

    }

    @Test
    public void updateOrder() {

    }

    @Test
    public void removeOrder() {

    }
}
