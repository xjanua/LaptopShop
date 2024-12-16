package com.example.Laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Laptopshop.domain.CartDetail;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
}
