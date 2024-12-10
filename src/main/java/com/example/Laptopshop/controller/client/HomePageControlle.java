package com.example.Laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageControlle {

    @GetMapping("/")
    public String getHomePage() {
        return "client/homepage/show";
    }
}
