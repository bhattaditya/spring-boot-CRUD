package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByPrice(Double price);
    List<Product> findByNameContaining(String name, Sort sort);

    @Query("FROM Product WHERE price <= :price")
    List<Product> lessThanEqualToPrice(Double price, Sort sort);
}
