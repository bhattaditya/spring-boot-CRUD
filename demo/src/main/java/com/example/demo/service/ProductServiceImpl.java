package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts(Integer pageNumber,Integer pageSize) {
        Pageable pages = PageRequest.of(pageNumber,pageSize, Sort.Direction.DESC, "updatedOn");
        return productRepository.findAll(pages).getContent();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getSingleProduct(Long id) {
        Optional <Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new IllegalStateException("Product with id: " + id + " not found!");
        }
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("There is no such product with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public void updateProduct(Long id, Double price, String description, String manufacturer) {
        Product product = productRepository.
                                findById(id).
                                orElseThrow(()-> new IllegalStateException("Product with id: " + id + " does not exists"));

        if (price != null && price > 0 && !Objects.equals(product.getPrice(), price)) {
            product.setPrice(price);
        }
        if (description != null && description.length() <= 300 && !Objects.equals(product.getDescription(), description)) {
            product.setDescription(description);
        }
        if(manufacturer != null && manufacturer.length() <=50 && !Objects.equals(product.getManufacturer(), manufacturer));
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findByPrice(Double price) {
        return productRepository.findByPrice(price);
    }

    @Override
    public List<Product> findByNameContaining(String name) {
        Sort sort = Sort.by(Sort.Direction.DESC, "price");
        return productRepository.findByNameContaining(name, sort);
    }

    @Override
    public List<Product> lessThanEqualToPrice(Double price) {
        Sort sort = Sort.by(Sort.Direction.DESC, "price");
        return productRepository.lessThanEqualToPrice(price, sort);
    }

}

