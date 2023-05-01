package com.example.glovo.services;

import com.example.glovo.entities.ProductEntity;
import com.example.glovo.models.Product;
import com.example.glovo.repositories.OrderWithProductsRepository;
import com.example.glovo.repositories.ProductDataRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    private final ProductDataRepository productRepository = Mockito.mock(ProductDataRepository.class);
    private final OrderWithProductsRepository orderWithProductsRepository = Mockito.mock(OrderWithProductsRepository.class);
    private ProductService productService;
    private Product expected;
    private ProductEntity productEntity;

    @BeforeEach
    public void init() {
        this.productService = new ProductService(productRepository, orderWithProductsRepository);
        this.expected = Product.builder().id(23).cost(34.5).name("Apple").dateOfPull(LocalDate.now()).build();
        this.productEntity = ProductEntity.builder().id(23).cost(34.5).name("Apple").build();
    }

    @Test
    public void getProductByIdTest() {
        Mockito.doReturn(Optional.ofNullable(productEntity)).when(productRepository).findById(23);
        Product actual = productService.getProductById(23);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getProductByIdExceptionTest() {
        Mockito.doThrow(ResponseStatusException.class).when(productRepository).findById(-23);
        Assertions.assertThrows(ResponseStatusException.class, () -> productService.getProductById(-23));
    }

    @Test
    public void addProductTest() {
        Mockito.when(productRepository.save(productEntity)).thenReturn(productEntity);
        Product actual = productService.addProduct(expected);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void changeProductTest() {
        Mockito.when(productRepository.save(productEntity)).thenReturn(productEntity);
        Product actual = productService.changeProduct(expected);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void removeProductTest() {
        Mockito.doReturn(0).when(orderWithProductsRepository).countProductsByIdInOrderWithProducts(expected.getId());
        Mockito.doReturn(false).when(productRepository).existsById(expected.getId());
        boolean actual = productService.removeProduct(expected);
        Mockito.verify(orderWithProductsRepository, Mockito.times(1)).deleteCertainProduct(expected.getId());
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(expected.getId());
        Assertions.assertTrue(actual);
    }
}
