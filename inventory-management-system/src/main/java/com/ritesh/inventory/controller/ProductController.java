package com.ritesh.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.ritesh.inventory.dto.ProductRequest;
import com.ritesh.inventory.dto.ProductResponse;
import com.ritesh.inventory.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // POST API - Save a new product
    @PostMapping
    public ProductResponse addProduct(
            @Valid @RequestBody ProductRequest request) {

        return productService.saveProduct(request);
    }

    // GET API - Fetch all products with pagination and sorting
    @GetMapping
    public Page<ProductResponse> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return productService.getAllProducts(
                page, size, sortBy, direction);
    }

    // GET API - Search products by name
    @GetMapping("/search/name")
    public List<ProductResponse> searchByName(
            @RequestParam String name) {

        return productService.searchByName(name);
    }

    // GET API - Search products by category
    @GetMapping("/search/category")
    public List<ProductResponse> searchByCategory(
            @RequestParam String category) {

        return productService.searchByCategory(category);
    }

    // GET API - Fetch product by ID
    @GetMapping("/{id}")
    public ProductResponse getProductById(
            @PathVariable Long id) {

        return productService.getProductById(id);
    }

    // PUT API - Update product
    @PutMapping("/{id}")
    public ProductResponse updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        return productService.updateProduct(id, request);
    }

    // DELETE API - Delete product
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);
    }
}