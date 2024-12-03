package com.example.Laptopshop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Laptopshop.domain.User;
import com.example.Laptopshop.repository.UserRespository;

@Service
public class UserService {
    private final UserRespository userRespository;

    public UserService(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    public List<User> getAllUser() {
        return this.userRespository.findAll();
    }

    public List<User> getAllUserbyEmail(String email) {
        return this.userRespository.findByEmail(email);
    }

    public User getUserbyId(long id) {
        return this.userRespository.findById(id);
    }

    public User handleSavUser(User user) {
        User Client = this.userRespository.save(user);
        return Client;
    }

    public void deleteUser(long id) {
        this.userRespository.deleteById(id);
    }
}
