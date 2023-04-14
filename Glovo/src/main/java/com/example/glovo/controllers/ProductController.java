package com.example.glovo.controllers;

import com.example.glovo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getCertainProduct(@PathVariable int id){
        return null;
    }
    @PostMapping
    public Product createProduct(@RequestBody  Product product){
        return null;
    }
    @PutMapping
    public Product updateProduct(@RequestBody Product product){
        return null;
    }
    @DeleteMapping
    public Product deleteProduct(@RequestBody Product product){
        return null;
    }
}
