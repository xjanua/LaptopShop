package com.example.Laptopshop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Laptopshop.domain.Role;
import com.example.Laptopshop.domain.User;
import com.example.Laptopshop.domain.DTO.RegisterDTO;
import com.example.Laptopshop.repository.RoleRepository;
import com.example.Laptopshop.repository.UserRespository;

@Service
public class UserService {
    private final UserRespository userRespository;
    private final RoleRepository roleRepository;

    public UserService(UserRespository userRespository, RoleRepository roleRepository) {
        this.userRespository = userRespository;
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUser() {
        return this.userRespository.findAll();
    }

    public List<User> getAllUserbyEmail(String email) {
        return this.userRespository.findOneByEmail(email);
    }

    public User getUserbyId(long id) {
        return this.userRespository.findById(id);
    }

    public User handleSaveUser(User user) {
        User Client = this.userRespository.save(user);
        return Client;
    }

    public void deleteUser(long id) {
        this.userRespository.deleteById(id);
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setFullname(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassWord());
        return user;
    }

    public boolean checkEmailExit(String email) {
        return this.userRespository.existsByEmail(email);
    }

    public User getUserByEmail(String Email) {
        return this.userRespository.findByEmail(Email);
    }
}
