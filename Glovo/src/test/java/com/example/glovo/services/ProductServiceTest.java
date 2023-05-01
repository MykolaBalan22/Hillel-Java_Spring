package com.example.glovo.services;

import com.example.glovo.entities.ProductEntity;
import com.example.glovo.models.Product;
import com.example.glovo.repositories.OrderWithProductsRepository;
import com.example.glovo.repositories.ProductDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    private ProductDataRepository productRepository = Mockito.mock(ProductDataRepository.class);
    private OrderWithProductsRepository orderWithProductsRepository = Mockito.mock(OrderWithProductsRepository.class);
    private ProductService productService;
    private Product expected;
    private ProductEntity productEntity;
    @BeforeEach
    public void init(){
        this.productService =new ProductService(productRepository,orderWithProductsRepository);
        this.expected =Product.builder().id(23).cost(34.5).name("Apple").dateOfPull(LocalDate.now()).build();
        this.productEntity=ProductEntity.builder().id(23).cost(34.5).name("Apple").build();
    }
    @Test
    public void getProductByIdTest() {

    }
    @Test
    public void getProductByIdExceptionTest() {

    }

    @Test
    public void addProductTest() {

    }

    @Test
    public void changeProductTest() {

    }

    @Test
    public void removeProductTest() {

    }
}
