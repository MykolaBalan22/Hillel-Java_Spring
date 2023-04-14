package com.example.glovo.services;

import com.example.glovo.models.Product;
import com.example.glovo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product getProductById(int id){
        return null;
    }
    public Product addProduct(Product product){
        return null;
    }
    public Product changeProduct(Product product){
        return null;
    }
    public Product  removeProduct(Product product){
        return null;
    }
}
