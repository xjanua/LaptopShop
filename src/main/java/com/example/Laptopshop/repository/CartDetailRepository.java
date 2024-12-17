package com.example.Laptopshop.repository;

import com.example.Laptopshop.domain.Cart;
import com.example.Laptopshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Laptopshop.domain.CartDetail;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    boolean existsByCartAndProduct(Cart cart, Product product);

    CartDetail findByCartAndProduct(Cart cart, Product product);

}
