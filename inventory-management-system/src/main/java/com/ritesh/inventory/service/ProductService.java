package com.ritesh.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ritesh.inventory.dto.ProductRequest;
import com.ritesh.inventory.dto.ProductResponse;
import com.ritesh.inventory.entity.Product;
import com.ritesh.inventory.exception.ProductNotFoundException;
import com.ritesh.inventory.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Convert Product entity to ProductResponse
    private ProductResponse convertToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory()
        );
    }

    // Save a new product
    public ProductResponse saveProduct(ProductRequest request) {

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(request.getCategory());

        Product savedProduct = productRepository.save(product);

        return convertToResponse(savedProduct);
    }

    // Get all products with pagination and sorting
    public Page<ProductResponse> getAllProducts(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return productRepository.findAll(pageable)
                .map(this::convertToResponse);
    }

    // Search products by name
    public List<ProductResponse> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // Search products by category
    public List<ProductResponse> searchByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // Get product by ID
    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found with id: " + id));

        return convertToResponse(product);
    }

    // Update an existing product
    public ProductResponse updateProduct(
            Long id,
            ProductRequest request) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found with id: " + id));

        existingProduct.setName(request.getName());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setCategory(request.getCategory());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setQuantity(request.getQuantity());

        Product updatedProduct = productRepository.save(existingProduct);

        return convertToResponse(updatedProduct);
    }

    // Delete product by ID
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found with id: " + id));

        productRepository.delete(product);
    }
}