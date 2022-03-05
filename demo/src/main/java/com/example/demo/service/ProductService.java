package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getProducts(Integer pageNumber, Integer pageSize);
    Product saveProduct(Product product);
    Optional<Product> getSingleProduct(Long id);
    void deleteProduct(Long id);
    void updateProduct(Long id, Double price, String description, String manufacturer);
    List<Product> findByName(String name);
    List<Product> findByPrice(Double price);
    List<Product> findByNameContaining(String name);
    List<Product> lessThanEqualToPrice(Double price);

}
