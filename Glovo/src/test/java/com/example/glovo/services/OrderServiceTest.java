package com.example.glovo.services;

import com.example.glovo.entities.OrderEntity;
import com.example.glovo.entities.ProductEntity;
import com.example.glovo.models.Order;
import com.example.glovo.models.Product;
import com.example.glovo.repositories.OrderDataRepository;
import com.example.glovo.repositories.OrderWithProductsRepository;
import com.example.glovo.repositories.ProductDataRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    private Order expected;
    private OrderEntity orderEntity;
    private List<ProductEntity> productEntities;
    private OrderService orderService;

    @BeforeEach
    public void init() {
        this.expected = Order.builder().id(23).date(LocalDate.now()).cost(34.5).build();
        List<Product> products = List.of(Product.builder().id(3).name("Apple").cost(4.6).dateOfPull(LocalDate.now()).build());
        this.expected.setProducts(products);
        this.orderEntity = OrderEntity.builder().id(23).date(LocalDate.now()).cost(34.5).build();
        this.productEntities = List.of(ProductEntity.builder().id(3).name("Apple").cost(4.6).build());
        this.orderService = new OrderService(orderRepository, orderWithProductsRepository, productDataRepository);
    }


    @Test
    public void getOrderByIdTest() {
        Mockito.doReturn(Optional.of(orderEntity)).when(orderRepository).findById(23);
        Mockito.doReturn(productEntities).when(productDataRepository).getProductEntityByCertainOrder(23);

        Order actual = orderService.getOrderById(23);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getOrderByIdExceptionTest() {
        Mockito.doThrow(ResponseStatusException.class).when(orderRepository).findById(-23);
        Assertions.assertThrows(ResponseStatusException.class, () -> orderService.getOrderById(-23));
    }


    @Test
    public void addOrder() {
        Mockito.when(orderRepository.save(Mockito.any(OrderEntity.class))).thenReturn(orderEntity);
        Mockito.doReturn(productEntities).when(productDataRepository).getProductEntityByCertainOrder(23);

        Order actual = orderService.addOrder(this.expected);

        Mockito.verify(orderWithProductsRepository, Mockito.times(1)).addProductByOrder(23, 3, LocalDate.now());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void updateOrder() {
        Mockito.when(orderRepository.save(Mockito.any(OrderEntity.class))).thenReturn(orderEntity);
        Mockito.doReturn(productEntities).when(productDataRepository).getProductEntityByCertainOrder(23);

        Order actual = orderService.updateOrder(this.expected);

        Mockito.verify(orderWithProductsRepository, Mockito.times(1)).deleteProductsForOrder(23);
        Mockito.verify(orderWithProductsRepository, Mockito.times(1)).addProductByOrder(23, 3, LocalDate.now());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void removeOrder() {

    }
}
