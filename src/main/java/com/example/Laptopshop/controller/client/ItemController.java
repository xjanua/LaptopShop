package com.example.Laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Laptopshop.domain.Product;
import com.example.Laptopshop.services.ProductService;

@Controller
public class ItemController {
    private final ProductService productService;

    public ItemController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String getProductPage(Model model, @PathVariable long id) {
        Product product = this.productService.getProductByID(id);
        model.addAttribute("product", product);
        return "client/product/detail";
    }

    // @GetMapping("/product")
    // public String getProductPage(Model model) {
    // return "client/product/detail";
    // }
}
