package com.example.glovo.services;

import com.example.glovo.entities.ProductEntity;
import com.example.glovo.entities.converters.ProductEntityConverter;
import com.example.glovo.models.Product;
import com.example.glovo.repositories.OrderWithProductsRepository;
import com.example.glovo.repositories.ProductDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {
    private final ProductDataRepository productRepository;
    private final OrderWithProductsRepository orderWithProductsRepository;

    @Autowired
    public ProductService(ProductDataRepository productRepository, OrderWithProductsRepository orderWithProductsRepository) {
        this.productRepository = productRepository;
        this.orderWithProductsRepository = orderWithProductsRepository;
    }

    public Product getProductById(int id) {
        ProductEntity entity = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        return ProductEntityConverter.productEntityToProduct(entity);
    }

    public Product addProduct(Product product) {
        ProductEntity entity = productRepository.save(ProductEntityConverter.productToProductEntity(product));
        return ProductEntityConverter.productEntityToProduct(entity);
    }

    public Product changeProduct(Product product) {
        ProductEntity entity = productRepository.save(ProductEntityConverter.productToProductEntity(product));
        return ProductEntityConverter.productEntityToProduct(entity);
    }

    public boolean removeProduct(Product product) {
        orderWithProductsRepository.deleteCertainProduct(product.getId());
        int numberOfproducts = orderWithProductsRepository.countProductsByIdInOrderWithProducts(product.getId());
        productRepository.deleteById(product.getId());
        return (numberOfproducts==0)&&!productRepository.existsById(product.getId());
    }

}
