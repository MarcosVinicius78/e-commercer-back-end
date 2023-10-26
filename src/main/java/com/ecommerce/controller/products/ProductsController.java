package com.ecommerce.controller.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    
    @Autowired
    private ProductRepository productRepository;

    @GetMapping()
    public Page<Product> getProducts(@RequestParam Long id, int page, int size){
        Pageable pageable = PageRequest.of(page, size);

        return this.productRepository.findByCategoryId(id, pageable );
    }

    @GetMapping("/findByNameContaining")
    public Page<Product> getSearchProduct(@RequestParam String name, int page, int size){
        Pageable pageable = PageRequest.of(page, size);

        return this.productRepository.findByNameContaining(name, pageable);
    }
}
