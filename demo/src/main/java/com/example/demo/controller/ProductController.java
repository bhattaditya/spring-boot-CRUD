package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProduct(@RequestParam Integer pageNumber,
                                    @RequestParam Integer pageSize) {
        return new ResponseEntity<>(productService.getProducts(pageNumber, pageSize), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED) ;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.OK) ;
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<HttpStatus> updateProduct(@PathVariable Long id,
                                                    @RequestParam(required = false) Double price,
                                                    @RequestParam(required = false) String description,
                                                    @RequestParam(required = false) String manufacturer
                                                   ) {
        productService.updateProduct(id, price, description, manufacturer);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/products/filter_name")
    public ResponseEntity<List<Product>> findByName(@RequestParam String name) {
        return new ResponseEntity<>(productService.findByName(name), HttpStatus.OK);
    }

    @GetMapping("products/filter_price")
    public ResponseEntity<List<Product>> findByPrice(@RequestParam Double price) {
        return new ResponseEntity<>(productService.findByPrice(price), HttpStatus.OK);
    }

    @GetMapping("products/filter_keyword_name")
    public ResponseEntity<List<Product>> findByKeyword(@RequestParam String name) {
        return new ResponseEntity<>(productService.findByNameContaining(name), HttpStatus.OK);
    }

    @GetMapping("products/filter_price_lte")
    public ResponseEntity<List<Product>> lessThanEqualToPrice(@RequestParam Double price) {
        return new ResponseEntity<>(productService.lessThanEqualToPrice(price), HttpStatus.OK);
    }

}
