package com.example.Laptopshop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Laptopshop.domain.Product;
import com.example.Laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product handleSaveProduct(Product product) {
        Product saveProduct = this.productRepository.save(product);
        return saveProduct;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductByID(long id) {
        Product getProduct = this.productRepository.findById(id);
        return getProduct;
    }

    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }
}
