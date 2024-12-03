package com.example.Laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Laptopshop.domain.User;

@Repository
public interface UserRespository extends JpaRepository<User, Long> {
    User save(User user);

    void deleteById(Long id);

    List<User> findByEmail(String email);

    List<User> findAll();

    User findById(long id);
}