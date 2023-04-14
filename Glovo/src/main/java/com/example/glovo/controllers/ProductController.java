package com.example.glovo.controllers;

import com.example.glovo.models.Product;
import com.example.glovo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getCertainProduct(@PathVariable int id){
        return productService.getProductById(id);
    }
    @PostMapping
    public Product createProduct(@RequestBody  Product product){
        return productService.addProduct(product);
    }
    @PutMapping
    public Product updateProduct(@RequestBody Product product){
        return productService.changeProduct(product);
    }
    @DeleteMapping
    public boolean deleteProduct(@RequestBody Product product){
        return productService.removeProduct(product);
    }
}
