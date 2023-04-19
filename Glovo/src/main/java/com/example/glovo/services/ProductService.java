package com.example.glovo.services;

import com.example.glovo.models.Product;
import com.example.glovo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(int id) {
        return productRepository.get(id);
    }

    public Product addProduct(Product product) {
        product.setId(ThreadLocalRandom.current().nextInt(1, 20000));
        return productRepository.add(product);
    }

    public Product changeProduct(Product product) {
        return productRepository.update(product);
    }

    public boolean removeProduct(Product product) {
        boolean deletedInAllOrers = productRepository.removeCertainProductForAllOrders(product.getId());
        boolean deletedInProducts = productRepository.remove(product.getId());
        return deletedInProducts && deletedInAllOrers;
    }
}
