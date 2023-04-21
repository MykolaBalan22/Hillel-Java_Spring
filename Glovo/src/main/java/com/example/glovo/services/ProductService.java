package com.example.glovo.services;

import com.example.glovo.entities.ProductEntity;
import com.example.glovo.entities.converters.ProductEntityConverter;
import com.example.glovo.models.Product;
import com.example.glovo.repositories.ProductDataRepository;
import com.example.glovo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class ProductService {
    private final ProductDataRepository productRepository;

    @Autowired
    public ProductService(ProductDataRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(int id) {
        ProductEntity entity = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        return ProductEntityConverter.productEntityToProduct(entity);
    }

    public Product addProduct(Product product) {
        ProductEntity entity = productRepository.save(ProductEntityConverter.productToProductEntity(product));
        return ProductEntityConverter.productEntityToProduct(entity);
    }
//
//    public Product changeProduct(Product product) {
//        return productRepository.update(product);
//    }
//
//    public boolean removeProduct(Product product) {
//        boolean deletedInAllOrers = productRepository.removeCertainProductForAllOrders(product.getId());
//        boolean deletedInProducts = productRepository.remove(product.getId());
//        return deletedInProducts && deletedInAllOrers;
//    }
}
