package com.example.Laptopshop.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Laptopshop.domain.Product;
import com.example.Laptopshop.domain.DTO.RegisterDTO;
import com.example.Laptopshop.services.ProductService;

@Controller
public class HomePageControlle {
    private final ProductService productService;

    public HomePageControlle(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("ListProduct", products);
        return "client/homepage/show";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("registerUser") RegisterDTO registerDTO) {
        // Xử lý logic đăng ký
        return "client/auth/register";
    }
}
