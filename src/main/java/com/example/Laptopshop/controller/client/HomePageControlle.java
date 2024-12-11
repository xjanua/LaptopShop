package com.example.Laptopshop.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Laptopshop.domain.Product;
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
}
