package com.ritesh.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.ritesh.inventory.dto.ProductRequest;
import com.ritesh.inventory.dto.ProductResponse;
import com.ritesh.inventory.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@Tag(name = "Product Controller", description = "APIs for managing inventory products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // POST API - Save a new product
    @PostMapping
    @Operation(
        summary = "Create a new product",
        description = "Adds a new product to the inventory"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Product created successfully"),
        @ApiResponse(responseCode = "400", description = "Validation failed")
    })
    public ProductResponse addProduct(
            @Valid @RequestBody ProductRequest request) {

        return productService.saveProduct(request);
    }

    // GET API - Fetch all products with pagination and sorting
    @GetMapping
    @Operation(
        summary = "Get all products",
        description = "Retrieves products with pagination and sorting"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Products retrieved successfully")
    })
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
    @Operation(
        summary = "Search products by name",
        description = "Finds products whose name contains the given text"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Products found successfully")
    })
    public List<ProductResponse> searchByName(
            @RequestParam String name) {

        return productService.searchByName(name);
    }

    // GET API - Search products by category
    @GetMapping("/search/category")
    @Operation(
        summary = "Search products by category",
        description = "Finds products belonging to the specified category"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Products found successfully")
    })
    public List<ProductResponse> searchByCategory(
            @RequestParam String category) {

        return productService.searchByCategory(category);
    }

    // GET API - Fetch product by ID
    @GetMapping("/{id}")
    @Operation(
        summary = "Get product by ID",
        description = "Retrieves a single product using its ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Product found successfully"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ProductResponse getProductById(
            @PathVariable Long id) {

        return productService.getProductById(id);
    }

    // PUT API - Update product
    @PutMapping("/{id}")
    @Operation(
        summary = "Update a product",
        description = "Updates an existing product using its ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Product updated successfully"),
        @ApiResponse(responseCode = "400", description = "Validation failed"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ProductResponse updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        return productService.updateProduct(id, request);
    }

    // DELETE API - Delete product
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete product",
        description = "Deletes an existing product using its ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public void deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);
    }
}